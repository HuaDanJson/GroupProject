package a.cool.groupproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.cool.groupproject.R;
import a.cool.groupproject.adapter.SelectGroupAdapter;
import a.cool.groupproject.bean.GroupBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupActivity extends AppCompatActivity {

    @BindView(R.id.iv_back_group_activity) ImageView mBack;
    @BindView(R.id.tv_sure_group_activity) TextView mSure;
    @BindView(R.id.rlv_group_activity) RecyclerView mRecyclerView;

    private SelectGroupAdapter mSelectGroupAdapter;
    private ArrayList<GroupBean> groupBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        groupBeanList = getIntent().getParcelableArrayListExtra("data");
        initRecyclerView(groupBeanList);
    }

    public void initRecyclerView(List<GroupBean> groupBeans) {
        if (mRecyclerView == null) {return;}
        if (mSelectGroupAdapter == null) {
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mSelectGroupAdapter = new SelectGroupAdapter();
            mSelectGroupAdapter.setDataSilently(groupBeans);
            mRecyclerView.setAdapter(mSelectGroupAdapter);
        } else {
            mSelectGroupAdapter.setData(groupBeans);
        }
    }

    @OnClick(R.id.iv_back_group_activity)
    public void backClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.tv_sure_group_activity)
    public void onSureClicked() {
        ArrayList<GroupBean> selectedGroupBeanList = new ArrayList<>();
        if (mSelectGroupAdapter != null) {
            List<GroupBean> adapterGroupBeanList = mSelectGroupAdapter.getData();
            for (GroupBean groupBean : adapterGroupBeanList) {
                if (groupBean.isSelected()) {
                    selectedGroupBeanList.add(groupBean);
                }
            }
        }
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("data", selectedGroupBeanList);
        setResult(RESULT_OK, intent);
        onBackPressed();
    }
}

package a.cool.groupproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import a.cool.groupproject.R;
import a.cool.groupproject.adapter.ShowGroupAdapter;
import a.cool.groupproject.bean.GroupBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_select_group) LinearLayout mSelectGroupLinearLayout;
    @BindView(R.id.rlv_main_activity) RecyclerView mRecyclerView;
    private ShowGroupAdapter mShowGroupAdapter;
    private List<GroupBean> mGroupBeanList = new ArrayList<>();

    private ArrayList<GroupBean> mALLGroupBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        for (int i = 0; i < 60; i++) {
            GroupBean groupBean = new GroupBean();
            groupBean.setGroupName("Group " + (i + 1));
            groupBean.setId(i + 1);
            groupBean.setSelected(false);
            mALLGroupBeanList.add(groupBean);
        }
    }

    @OnClick(R.id.ll_select_group)
    public void selectGroupClicked() {
        if (mGroupBeanList == null || mGroupBeanList.isEmpty()) {
            for (GroupBean groupBean : mALLGroupBeanList) {
                groupBean.setSelected(false);
            }
        } else {
            for (GroupBean groupBean : mALLGroupBeanList) {
                if (isGroupBeanSelected(groupBean)) {
                    groupBean.setSelected(true);
                } else {
                    groupBean.setSelected(false);
                }
            }
        }
        Intent intent = new Intent(MainActivity.this, GroupActivity.class);
        intent.putParcelableArrayListExtra("data", mALLGroupBeanList);
        startActivityForResult(intent, 101);
    }

    public boolean isGroupBeanSelected(GroupBean groupBean) {
        for (GroupBean bean : mGroupBeanList) {
            if (groupBean.getId() == bean.getId()) {
                return true;
            }
        }
        return false;
    }

    public void initRecyclerView(List<GroupBean> groupBeans) {
        if (mRecyclerView == null) {return;}
        if (mShowGroupAdapter == null) {
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mShowGroupAdapter = new ShowGroupAdapter();
            mShowGroupAdapter.setDataSilently(groupBeans);
            mRecyclerView.setAdapter(mShowGroupAdapter);
        } else {
            mShowGroupAdapter.setData(groupBeans);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            ArrayList<GroupBean> list = data.getParcelableArrayListExtra("data");
            mGroupBeanList = list;
            initRecyclerView(mGroupBeanList);
        }
    }
}

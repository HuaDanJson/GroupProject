package a.cool.groupproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import a.cool.groupproject.R;
import a.cool.groupproject.base.BaseRVAdapter;
import a.cool.groupproject.base.IViewHolder;
import a.cool.groupproject.bean.GroupBean;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowGroupAdapter extends BaseRVAdapter<GroupBean, ShowGroupAdapter.ShowGroupAdapterHolder> {

    @Override
    protected ShowGroupAdapterHolder doCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowGroupAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_group_adapter, parent, false));
    }

    @Override
    protected void bindItemData(ShowGroupAdapterHolder viewHolder, GroupBean groupBean, int position) {
        viewHolder.bindView(groupBean, position);
    }

    public class ShowGroupAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<GroupBean> {

        @BindView(R.id.riv_item_show_group_adapter)
        ImageView mAvatar;
        @BindView(R.id.tv_name_item_show_group_adapter)
        TextView mUserName;

        public ShowGroupAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(GroupBean groupBean, int position) {
            mUserName.setText(groupBean.getGroupName());
        }
    }
}

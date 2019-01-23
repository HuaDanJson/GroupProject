package a.cool.groupproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.cool.groupproject.R;
import a.cool.groupproject.base.BaseRVAdapter;
import a.cool.groupproject.base.IViewHolder;
import a.cool.groupproject.bean.GroupBean;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectGroupAdapter extends BaseRVAdapter<GroupBean, SelectGroupAdapter.SelectGroupAdapterHolder> {

    @Override
    protected SelectGroupAdapterHolder doCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectGroupAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message_adapter, parent, false));
    }

    @Override
    protected void bindItemData(SelectGroupAdapterHolder viewHolder, GroupBean groupBean, int position) {
        viewHolder.bindView(groupBean, position);
    }

    public class SelectGroupAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<GroupBean> {

        @BindView(R.id.riv_item_chat_message_adapter)
        ImageView mAvatar;
        @BindView(R.id.tv_name_item_chat_message_adapter)
        TextView mUserName;
        @BindView(R.id.iv_select_item_group_adapter)
        ImageView mSelectImage;
        @BindView(R.id.ll_all_riv_item_select_group_adapter)
        LinearLayout mSelectLinearLayout;

        public SelectGroupAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(final GroupBean groupBean, final int position) {
            if (groupBean == null) {return;}
            mUserName.setText(groupBean.getGroupName());
            if (groupBean.isSelected()) {
                mSelectImage.setSelected(true);
            } else {
                mSelectImage.setSelected(false);
            }
            mSelectLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (groupBean == null) {return;}
                    if (groupBean.isSelected()) {
                        groupBean.setSelected(false);
                    } else {
                        groupBean.setSelected(true);
                    }
                    notifyItemChanged(position);
                }
            });
        }
    }
}

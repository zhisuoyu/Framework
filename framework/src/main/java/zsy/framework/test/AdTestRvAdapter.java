package zsy.framework.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import zsy.framework.R;


public class AdTestRvAdapter extends RecyclerView.Adapter<AdTestRvAdapter.VH> {

    private RecyclerView rv;
    private List<RvTestEntity> mDatas;
    private OnItemClickLsn onItemClickLsn;

    public AdTestRvAdapter(List<RvTestEntity> datas) {
        this.mDatas = datas;
    }

    public void setOnItemClickLsn(OnItemClickLsn onItemClickLsn) {
        this.onItemClickLsn = onItemClickLsn;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rv = (RecyclerView) parent;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = null;
        switch (viewType) {
            case RvTestEntity.TYPE_EMPTY:
                itemView = new View(parent.getContext());
                break;
            case RvTestEntity.TYPE_TV:
                itemView = inflater.inflate(R.layout.item_test_rv_tv, parent, false);
                break;
            case RvTestEntity.TYPE_BTN:
                itemView = inflater.inflate(R.layout.item_test_rv_btn, parent, false);
                break;
            case RvTestEntity.TYPE_ET:
                itemView = inflater.inflate(R.layout.item_test_rv_et, parent, false);
                break;
        }
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final int p = position;
        if (mDatas.get(p).getType() == RvTestEntity.TYPE_EMPTY) {
            return;
        }
        ((TextView) holder.itemView).setText(mDatas.get(position).getText());
        if (getItemViewType(position) == RvTestEntity.TYPE_ET) {
            ((EditText) holder.itemView).setHint(mDatas.get(position).getHint());
        } else if (onItemClickLsn != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickLsn.onItemClick(p);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
            if (itemView instanceof EditText) {
                ((EditText) itemView).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mDatas.get(getAdapterPosition()).setText(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }
    }


    public interface OnItemClickLsn {
        void onItemClick(int position);
    }

    public String getText(int position) {
//        TextView tv = (TextView) rv.getLayoutManager().findViewByPosition(position);
        return mDatas.get(position).getText();
    }


}

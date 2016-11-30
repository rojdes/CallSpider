package org.d_m_n.callspider.callspider.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/30/16.
 */

public class ItemContactView extends RelativeLayout implements View.OnClickListener {

    public interface ItemContactClickListener{

        public void onDirectionChanged(ContactCallForbidDirection d);
    }

    @BindView(R.id.tv_item_contact_list_name)
    public TextView tvContactName;

    @BindView(R.id.tv_item_contact_list_number)
    public TextView tvContactNumber;


    @BindView(R.id.iv_item_contact_category_item_icon)
    public ImageView ivContactCategoryIcon;

    @BindView(R.id.iv_item_contact_category_item_direction)
    public ImageView ivContactDirection;

    @BindView(R.id.iv_direction_select_arrow_up)
    protected ImageView mivUp;

    @BindView(R.id.iv_direction_select_arrow_down)
    protected ImageView mivDown;

    @BindView(R.id.iv_direction_select_arrow_both)
    protected ImageView mivBoth;

    @BindView(R.id.iv_direction_select_arrow_unknown)
    protected ImageView mivUnknown;

    @BindView(R.id.llt_item_contact_category_direction_select)
    protected LinearLayout lltDirectionContact;

    @BindView(R.id.rlt_item_contact_info)
    protected RelativeLayout rltContactInfo;

    private ItemContactClickListener clickListener;

    private CommonContact mContact;


    public ItemContactView(Context context) {
        super(context);
        init(context);
    }

    public ItemContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View v =inflate(context, R.layout.incl_item_contact_list, this);
        ButterKnife.bind(this,v);
        mivUp.setOnClickListener(this);
        mivDown.setOnClickListener(this);
        mivBoth.setOnClickListener(this);
        mivUnknown.setOnClickListener(this);
        ivContactDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectDirection();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_direction_select_arrow_up:
                handleDirectionClick(ContactCallForbidDirection.OUTGOING);
                break;
            case R.id.iv_direction_select_arrow_down:
                handleDirectionClick(ContactCallForbidDirection.INCOMING);
                break;
            case R.id.iv_direction_select_arrow_both:
                handleDirectionClick(ContactCallForbidDirection.FULL);
                break;
            case R.id.iv_direction_select_arrow_unknown:
                handleDirectionClick(ContactCallForbidDirection.NOT_SET);
                break;
            default:
                break;

        }
    }

    private void handleDirectionClick(ContactCallForbidDirection d) {
        hideItem(d);
        setContactDirection(d);
        if(clickListener != null){
            clickListener.onDirectionChanged(d);
        }

    }

    public void hideItem(ContactCallForbidDirection d){
        mivUp.setVisibility(VISIBLE);
        mivDown.setVisibility(VISIBLE);
        mivBoth.setVisibility(VISIBLE);
        mivUnknown.setVisibility(VISIBLE);
        switch (d){
            case FULL:
                mivBoth.setVisibility(GONE); break;
            case OUTGOING:
                mivUp.setVisibility(GONE); break;
            case INCOMING:
                mivDown.setVisibility(GONE); break;
            case NOT_SET:
                mivUnknown.setVisibility(GONE); break;
        }
    }

    public void setContact(CommonContact contact){
        mContact = contact;
        tvContactName.setText(String.valueOf(contact.name));
        tvContactNumber.setText(String.valueOf(contact.number));
        setContactDirection(contact.direction);
    }

    public void setContactDirection(ContactCallForbidDirection d){
        mContact.direction = d;
        ivContactDirection.setImageDrawable(d.getDrawable(ivContactDirection.getContext()));
    }

    public void toggleVisibility() {
        setVisibility(isVisible() ? GONE : VISIBLE);
    }

    public void hideSelectDirection(){
        lltDirectionContact.setVisibility(GONE);
    }

    public void showSelectDirection(){
        lltDirectionContact.setVisibility(VISIBLE);
    }

    public boolean isVisible(){
        return getVisibility() == VISIBLE;
    }

    public void setItemClickListener(ItemContactClickListener onClickListener) {
        this.clickListener = onClickListener;
    }
}

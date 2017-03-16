package org.d_m_n.callspider.callspider.ui.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.enums.ForbiddenDirection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/30/16.
 */

public class ItemContactView extends RelativeLayout implements View.OnClickListener {

    private static final int ANIMATION_OFFSET = 10;
    private static final long ANIMATION_DURATION = 300;

    public interface ItemContactClickListener{

        public void onDirectionChanged(ForbiddenDirection d);
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
                if (lltDirectionContact.getVisibility() == INVISIBLE)
                    showSelectDirection();
                else
                    hideSelectDirection();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_direction_select_arrow_up:
                handleDirectionClick(ForbiddenDirection.OUTGOING);
                break;
            case R.id.iv_direction_select_arrow_down:
                handleDirectionClick(ForbiddenDirection.INCOMING);
                break;
            case R.id.iv_direction_select_arrow_both:
                handleDirectionClick(ForbiddenDirection.FULL);
                break;
            case R.id.iv_direction_select_arrow_unknown:
                handleDirectionClick(ForbiddenDirection.NOT_SET);
                break;
            default:
                break;

        }
    }

    private void handleDirectionClick(ForbiddenDirection d) {
        hideItem(d);
        setContactDirection(d);
        hideSelectDirection();
        if(clickListener != null){
            clickListener.onDirectionChanged(d);
        }

    }

    public void hideItem(ForbiddenDirection d){
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
        hideItem(contact.direction);
    }

    public void setContactDirection(ForbiddenDirection d){
        mContact.direction = d;
        ivContactDirection.setImageDrawable(d.getDrawable(ivContactDirection.getContext()));
    }

    public void hideSelectDirection(){
        ObjectAnimator mover = ObjectAnimator.ofFloat(rltContactInfo,
                View.TRANSLATION_X, -(lltDirectionContact.getWidth() + ANIMATION_OFFSET), 0F);
        mover.setDuration(ANIMATION_DURATION);
        mover.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivContactDirection.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ivContactDirection.setEnabled(true);
                lltDirectionContact.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mover.start();
    }

    public void showSelectDirection(){
        lltDirectionContact.setVisibility(VISIBLE);
        ObjectAnimator mover = ObjectAnimator.ofFloat(rltContactInfo,
                View.TRANSLATION_X, 0F, -(lltDirectionContact.getWidth() + ANIMATION_OFFSET));
        mover.setDuration(ANIMATION_DURATION);
        mover.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivContactDirection.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ivContactDirection.setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mover.start();
    }

    public void setItemClickListener(ItemContactClickListener onClickListener) {
        this.clickListener = onClickListener;
    }
}

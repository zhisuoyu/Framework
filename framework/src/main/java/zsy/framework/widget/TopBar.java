package zsy.framework.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zsy.framework.R;


public class TopBar extends RelativeLayout {

    private final int DEFAULT_TEXT_COLOR = Color.WHITE;

    private final float DEFAULT_TITLE_SIZE = 20f;
    private final float DEFAULT_SUB_TITLE_SIZE = 16f;

    private final int PADDING_DP = 20;
    private int padding_px;

    private OnSubClickListener onSubClickListener;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        padding_px = dp2px(context, PADDING_DP);
        int textColor = DEFAULT_TEXT_COLOR;
        String titleText = null;

        float titleSize = DEFAULT_TITLE_SIZE;

        float subTitleSize = DEFAULT_SUB_TITLE_SIZE;
        ColorStateList subColor = null;

        String leftText = null;
        Drawable leftDrawable = null;

        String rightText = null;
        Drawable rightDrawable = null;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.TopBar_textColor) {
                textColor = ta.getColor(attr, Color.WHITE);
            } else if (attr == R.styleable.TopBar_titleText) {
                titleText = ta.getString(attr);
            } else if (attr == R.styleable.TopBar_titleSize) {
                titleSize = ta.getDimension(attr, DEFAULT_TITLE_SIZE);
            } else if (attr == R.styleable.TopBar_subTitleSize) {
                subTitleSize = ta.getDimension(R.styleable.TopBar_subTitleSize, DEFAULT_SUB_TITLE_SIZE);
            } else if (attr == R.styleable.TopBar_subColor) {
                subColor = ta.getColorStateList(attr);
            } else if (attr == R.styleable.TopBar_leftText) {
                leftText = ta.getString(attr);
            } else if (attr == R.styleable.TopBar_leftDrawable) {
                leftDrawable = ta.getDrawable(attr);
            } else if (attr == R.styleable.TopBar_rightText) {
                rightText = ta.getString(attr);
            } else if (attr == R.styleable.TopBar_rightDrawable) {
                rightDrawable = ta.getDrawable(attr);
            }
        }
        ta.recycle();

        TextView titleTv = newTv(context, titleText, titleSize, null, textColor);
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(CENTER_IN_PARENT);
        addView(titleTv, titleParams);

        View leftV = newV(context, leftDrawable, leftText, subTitleSize, subColor, textColor);
        if (leftV != null) {
            LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.addRule(ALIGN_PARENT_LEFT);
            leftParams.addRule(CENTER_VERTICAL);
            addView(leftV, leftParams);
            leftV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSubClickListener != null) {
                        onSubClickListener.onLeftClick(v);
                    }
                }
            });
        }

        View rightV = newV(context, rightDrawable, rightText, subTitleSize, subColor, textColor);
        if (rightV != null) {
            LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightParams.addRule(ALIGN_PARENT_RIGHT);
            rightParams.addRule(CENTER_VERTICAL);
            addView(rightV, rightParams);
            rightV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSubClickListener != null) {
                        onSubClickListener.onRightClick(v);
                    }
                }
            });
        }

    }

    private TextView newTv(Context context, String text, float textSize, ColorStateList colorStateList, int defaultColor) {
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(padding_px, 0, padding_px, 0);
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(defaultColor);
        } else {
            tv.setFocusable(true);
            tv.setClickable(true);
        }
        tv.setTextColor(colorStateList);
//        tv.setBackgroundColor(Color.GREEN);
        return tv;
    }

    private View newV(Context context, Drawable drawable, String text, float textSize, ColorStateList colorStateList, int defaultColor) {
        View view = null;
        if (drawable != null) {
            ImageView iv = new ImageView(context);
            iv.setImageDrawable(drawable);
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view = iv;
//            iv.setBackgroundColor(Color.GREEN);
            iv.setPadding(padding_px, padding_px / 2, padding_px, padding_px / 2);
        } else if (text != null) {
            view = newTv(context, text, textSize, colorStateList, defaultColor);
        }
        return view;
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void setOnSubClickListener(OnSubClickListener onSubClickListener) {
        this.onSubClickListener = onSubClickListener;
    }

    public interface OnSubClickListener {

        void onLeftClick(View view);

        void onRightClick(View view);

    }


    //        if (TextUtils.isEmpty(leftText)) {
//            ImageView iv = getIv(context, leftDrawableLeft);
//            leftV = iv;
//        } else {
//            TextView tv = getTv(context, leftText, leftColor, leftSize);
//            tv.setCompoundDrawablesWithIntrinsicBounds(leftDrawableLeft, null, null, null);
//            tv.setCompoundDrawablePadding(leftDrawablePadding);
//            leftV = tv;
//        }
////        leftV.setPadding(leftLeftPadding, leftTopPadding, leftRightPadding, leftBottomPadding);
////        ViewUtils.setBg(leftV, leftBackground);
//
//        if (TextUtils.isEmpty(rightText)) {
//            ImageView iv = getIv(context, rightDrawableRight);
//            rightV = iv;
//        } else {
//            TextView tv = getTv(context, rightText, rightColor, rightSize);
//            tv.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawableRight, null);
//            tv.setCompoundDrawablePadding(rightDrawablePadding);
//            rightV = tv;
//        }
////        rightV.setPadding(rightLeftPadding, rightTopPadding, rightRightPadding, rightBottomPadding);
////        ViewUtils.setBg(rightV, rightBackground);
//
//
//        leftV.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onClickLsn != null) {
//                    onClickLsn.leftOnClick(leftV);
//                }
//            }
//        });
//        rightV.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onClickLsn != null) {
//                    onClickLsn.rightOnClick(rightV);
//                }
//            }
//        });
//
//
//        titleTv = getTv(context, titleText, color, titleSize);
//
//
//        leftParams = new LayoutParams(leftWidth, leftHeight);
//        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
//        addView(leftV, leftParams);
//
//        rightParams = new LayoutParams(rightWidth, rightHeight);
//        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
//        addView(rightV, rightParams);
//
//        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.MATCH_PARENT);
//        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        addView(titleTv, titleParams);
//    }
//

//    private TextView getTv(Context context, String text, float size, ColorStateList color,int defaultColor) {
//        TextView tv = new TextView(context);
//        tv.setAllCaps(false);
//        tv.setText(text);
//        tv.setTextColor(color != null ? color : ColorStateList.valueOf(defaultColor));
//        tv.setTextSize(size);
//        tv.setGravity(Gravity.CENTER);
//        return tv;
//    }

//    private ImageView getIv(Context context, Drawable drawable) {
//        ImageView iv = new ImageView(context);
//        iv.setImageDrawable(drawable);
//        return iv;
//    }
//
//    public void setOnClickLsn(OnClickLsn onClickLsn) {
//        this.onClickLsn = onClickLsn;
//    }

//    public interface OnClickLsn {
//        void leftOnClick(View v);
//
//        void rightOnClick(View v);
//    }

//    public static class SimpleOnClickLsn implements OnClickLsn {
//
//        @Override
//        public void leftOnClick(View v) {
//
//        }
//
//        @Override
//        public void rightOnClick(View v) {
//
//        }
//}

}

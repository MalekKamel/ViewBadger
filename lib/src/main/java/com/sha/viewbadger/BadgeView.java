package com.sha.viewbadger;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * A simple text label view that can be applied as a "badge" to any given {@link View}.
 * This class is intended to be instantiated at runtime rather than included in XML layouts.
 *
 * @author Jeff Gilfelt
 */
@SuppressLint("ViewConstructor")
public class BadgeView extends AppCompatTextView {
    
    public static final int MIN_SIZE = 50;

    private static final int DEFAULT_MARGIN_DIP = 5;
    private static final int DEFAULT_LR_PADDING_DIP = 5;
    private static final Position DEFAULT_POSITION = Position.TOP_END;
    private static final int DEFAULT_BADGE_COLOR = Color.RED;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;

    private static Animation fadeIn = AnimUtil.fadeIn();
    private static Animation fadeOut = AnimUtil.fadeOut();

    private int horizontalMargin;
    private int verticalMargin;

    private int badgeColor = DEFAULT_BADGE_COLOR;
    private boolean isShown;
    private boolean trimNumberAfter99 = true;

    private Position position;
    private long actualNumber;

    public enum Position {
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END,
        CENTER,
        CENTER_HORIZONTAL,
        CENTER_VERTICAL
    }

    private ShapeDrawable badgeBg;

    private BadgeParams params;

    public BadgeView(BadgeParams params) {
        super(params.context);
        this.params = params;
        init();
    }


    private void init() {
        setSingleLine(true);
        setSingleLine();

        setTextSize(11);

        // apply defaults
        position = DEFAULT_POSITION;
        horizontalMargin = dipToPixels(DEFAULT_MARGIN_DIP);
        verticalMargin = horizontalMargin;

        setTypeface(Typeface.DEFAULT_BOLD);
        setPadding(0, 0, 0, 0);
        setTextColor(DEFAULT_TEXT_COLOR);

        if (params.target != null) {
            applyToTarget();
        } else {
            show();
        }
    }

    private void applyToTarget() {

        switch (params.targetType){
            case TAB_LAYOUT:
               new TabLayoutSetup(this, params).setup();
                break;

            case BOTTOM_NAV:
                new BottomNavSetup(this, params).setup();
                break;

            case VIEW:
                new ViewSetup(this, params).setup();
                break;
        }
        setVisibility(View.GONE);
    }

    /**
     * Make the badge visible in the UI.
     *
     */
    public BadgeView show() {
        show(false, null);
        return this;
    }

    /**
     * Make the badge visible in the UI.
     *
     * @param animate flag to apply the default fade-in animation.
     */
    public BadgeView show(boolean animate) {
        show(animate, fadeIn);
        return this;
    }

    /**
     * Make the badge visible in the UI.
     *
     * @param anim Animation to apply to the view when made visible.
     */
    public BadgeView show(Animation anim) {
        show(true, anim);
        return this;
    }

    private void show(boolean animate, Animation anim) {
        isShown = true;

        applyBackground(getText());

        applyLayoutParams();

        this.setVisibility(View.VISIBLE);

        if (animate) {
            this.startAnimation(anim);
        }
    }

    public BadgeView setBadgeText(CharSequence text){
        setText(text, BufferType.NORMAL);
        return this;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        updateActualNumber(text);

        handleDigitLength(text, type);

        applyBackground(text);
    }

    private void updateActualNumber(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;

        if (TextUtils.isDigitsOnly(text)){
            try {
                actualNumber = Long.parseLong(text.toString());
            }catch (Exception e){
                e.printStackTrace();
                actualNumber = 0;
            }
            return;
        }

        actualNumber = 0;
    }

    private void applyBackground(CharSequence text) {
        if (!isShown) return;

        if (badgeBg != null){
            setBackgroundDrawable(badgeBg);
            return;
        }

        circleBackground(text);
    }

    private void circleBackground(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;

        Rect bounds = new Rect();

        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bounds);

        int w = bounds.width() + 40;
        w = w >= MIN_SIZE ? w : MIN_SIZE;

        setWidth(w);
        setHeight(w);

        setGravity(Gravity.CENTER);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(badgeColor);
        gd.setCornerRadius(w / 2);

        setBackground(gd);
    }

    private void applyLayoutParams() {
        if (params.targetType.equals(BadgeParams.TargetType.TAB_LAYOUT))
            return;

        Pair<Integer, Margins> positionInfo = getPositionInfo();

        if (getParent() instanceof LinearLayout){
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );

            llp.gravity = positionInfo.first;
            llp.setMargins(
                    positionInfo.second.start,
                    positionInfo.second.top,
                    positionInfo.second.end,
                    positionInfo.second.bottom
            );
            setLayoutParams(llp);
            return;
        }

        if (getParent() instanceof FrameLayout){
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );

            switch (params.targetType){
                case BOTTOM_NAV:
                    flp.gravity = Gravity.CENTER | Gravity.TOP;
                    flp.setMargins(
                            positionInfo.second.start,
                            positionInfo.second.top,
                            positionInfo.second.end,
                            positionInfo.second.bottom
                    );
                    break;

                default:
                    flp.gravity = positionInfo.first;

                    break;
            }

            setLayoutParams(flp);
        }
    }

    private class Margins {
        int start;
        int end;
        int top;
        int bottom;

        Margins setStart(int start) {
            this.start = start;
            return this;
        }

        Margins setEnd(int end) {
            this.end = end;
            return this;
        }

        Margins setTop(int top) {
            this.top = top;
            return this;
        }

        Margins setBottom(int bottom) {
            this.bottom = bottom;
            return this;
        }
    }

    private Pair<Integer, Margins> getPositionInfo() {
        int gravity = 0;

        Margins margins = new Margins();

        switch (position) {

            case TOP_START:
                gravity = Gravity.START | Gravity.TOP;
                margins = new Margins()
                        .setStart(horizontalMargin)
                        .setTop(verticalMargin);
                break;

            case TOP_END:
                gravity = Gravity.END | Gravity.TOP;
                margins = new Margins()
                        .setEnd(horizontalMargin)
                        .setTop(verticalMargin);
                break;

            case BOTTOM_START:
                gravity = Gravity.START | Gravity.BOTTOM;
                margins = new Margins()
                        .setStart(horizontalMargin)
                        .setBottom(verticalMargin);
                break;

            case BOTTOM_END:
                gravity = Gravity.END | Gravity.BOTTOM;
                margins = new Margins()
                        .setEnd(horizontalMargin)
                        .setBottom(verticalMargin);
                break;

            case CENTER:
                gravity = Gravity.CENTER;
                break;

            case CENTER_HORIZONTAL:
                gravity = Gravity.CENTER_HORIZONTAL;
                break;

            case CENTER_VERTICAL:
                gravity = Gravity.CENTER_VERTICAL;
                break;

            default:
                break;
        }

        return new Pair<>(gravity, margins);
    }

    private void handleDigitLength(CharSequence text, BufferType type) {

        if (!trimNumberAfter99){
            super.setText(actualNumber > 0 ? String.valueOf(actualNumber) : text, type);
            return;
        }

        if (TextUtils.isEmpty(text) || !TextUtils.isDigitsOnly(text)){
            super.setText(text, type);
            return;
        }

        int length = text.length();
        if (length <= 2){
            super.setText(text, type);
            return;
        }

        super.setText("99+", type);
    }

    /**
     * Make the badge non-visible in the UI.
     *
     */
    public BadgeView hide() {
        hide(false, null);
        return this;
    }

    /**
     * Make the badge non-visible in the UI.
     *
     * @param animate flag to apply the default fade-out animation.
     */
    public BadgeView hide(boolean animate) {
        hide(animate, fadeOut);
        return this;
    }

    /**
     * Make the badge non-visible in the UI.
     *
     * @param anim Animation to apply to the view when made non-visible.
     */
    public BadgeView hide(Animation anim) {
        hide(true, anim);
        return this;
    }

    /**
     * Toggle the badge visibility in the UI.
     *
     */
    public BadgeView toggle() {
        toggle(false, null, null);
        return this;
    }

    /**
     * Toggle the badge visibility in the UI.
     *
     * @param animate flag to apply the default fade-in/out animation.
     */
    public BadgeView toggle(boolean animate) {
        toggle(animate, fadeIn, fadeOut);
        return this;
    }

    /**
     * Toggle the badge visibility in the UI.
     *
     * @param animIn Animation to apply to the view when made visible.
     * @param animOut Animation to apply to the view when made non-visible.
     */
    public BadgeView toggle(Animation animIn, Animation animOut) {
        toggle(true, animIn, animOut);
        return this;
    }

    private void hide(boolean animate, Animation anim) {
        this.setVisibility(View.GONE);
        if (animate) {
            this.startAnimation(anim);
        }
        isShown = false;
    }

    private void toggle(boolean animate, Animation animIn, Animation animOut) {
        if (isShown) {
            hide(animate && (animOut != null), animOut);
        } else {
            show(animate && (animIn != null), animIn);
        }
    }

    /**
     * Increment the numeric badge label. If the current badge label cannot be converted to
     * an integer value, its label will be set to "0".
     *
     * @param offset the increment offset.
     */
    public int increment(int offset) {
        CharSequence txt = getText();

        if (TextUtils.isEmpty(txt)) return 0;
        if (!TextUtils.isDigitsOnly(txt)) return 0;

        int i = Integer.parseInt(txt.toString());

        i += offset;
        setText(String.valueOf(i));
        return i;
    }

    /**
     * Decrement the numeric badge label. If the current badge label cannot be converted to
     * an integer value, its label will be set to "0".
     *
     * @param offset the decrement offset.
     */
    public int decrement(int offset) {
        return increment(-offset);
    }

    /**
     * Returns the target View this badge has been attached to.
     *
     */
    public View getTarget() {
        return params.target;
    }

    /**
     * Is this badge currently visible in the UI?
     *
     */
    @Override
    public boolean isShown() {
        return isShown;
    }

    /**
     * Returns the positioning of this badge.
     *
     * one of TOP_START, TOP_END, BOTTOM_START, BOTTOM_END, POSTION_CENTER.
     *
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set the positioning of this badge.
     *
     * @param layoutPosition one of TOP_START, TOP_END, BOTTOM_START, BOTTOM_END, POSTION_CENTER.
     *
     */
    public BadgeView setPosition(Position layoutPosition) {
        this.position = layoutPosition;
        return this;
    }

    /**
     * Returns the horizontal margin from the target View that is applied to this badge.
     *
     */
    public int getHorizontalBadgeMargin() {
        return horizontalMargin;
    }

    /**
     * Returns the vertical margin from the target View that is applied to this badge.
     *
     */
    public int getVerticalBadgeMargin() {
        return verticalMargin;
    }

    /**
     * Set the horizontal/vertical margin from the target View that is applied to this badge.
     *
     * @param badgeMargin the margin in pixels.
     */
    public BadgeView setBadgeMargin(int badgeMargin) {
        this.horizontalMargin = badgeMargin;
        this.verticalMargin = badgeMargin;
        return this;
    }

    /**
     * Set the horizontal/vertical margin from the target View that is applied to this badge.
     *
     * @param horizontal margin in pixels.
     * @param vertical margin in pixels.
     */
    public BadgeView setBadgeMargin(int horizontal, int vertical) {
        this.horizontalMargin = horizontal;
        this.verticalMargin = vertical;
        return this;
    }

    /**
     * Returns the color value of the badge background.
     *
     */
    public int getBadgeBackgroundColor() {
        return badgeColor;
    }

    public long getActualNumber() {
        return actualNumber;
    }

    /**
     * Set the color value of the badge background.
     *
     * @param badgeColor the badge background color.
     */
    public BadgeView setBadgeBackgroundColor(int badgeColor) {
        this.badgeColor = badgeColor;
        applyBackground(getText());
        return this;
    }

    public BadgeView setTrimNumberAfter99(boolean trimNumberAfter99) {
        this.trimNumberAfter99 = trimNumberAfter99;
       update();
        return this;
    }

    public boolean isTrimNumberAfter99() {
        return trimNumberAfter99;
    }

    private int dipToPixels(int dip) {
      return PixelUtil.from(dip, getContext());
    }

    private void update(){
        handleDigitLength(getText(), BufferType.NORMAL);
        applyBackground(getText());
    }

}
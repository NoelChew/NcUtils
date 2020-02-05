package com.noelchew.ncutils.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by noelchew on 19/08/2016.
 */
public class AnimationUtil {
    public static void enterAnimateScaleXY(final View view, int delay, long duration, final AnimationUtilListener listener) {
        enterAnimateScaleXY(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void enterAnimateScaleXY(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setScaleX(0f);
        view.setScaleY(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void exitAnimateScaleXY(final View view, int delay, long duration, final AnimationUtilListener listener) {
        exitAnimateScaleXY(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void exitAnimateScaleXY(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setScaleX(1f);
        view.setScaleY(1f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.GONE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void enterAnimateScaleY(final View view, int delay, long duration, final AnimationUtilListener listener) {
        enterAnimateScaleY(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void enterAnimateScaleY(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setScaleX(1f);
        view.setScaleY(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void enterFromLeft(final View view, int delay, long duration, final AnimationUtilListener listener) {
        enterFromLeft(view, 0, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void enterFromLeft(final View view, float x, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setX(-1500f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .x(x)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void enterFromTop(final View view, int delay, long duration, final AnimationUtilListener listener) {
        enterFromTop(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void enterFromTop(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setY(-1000f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .y(0f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void exitToLeft(final View view, int delay, long duration, final AnimationUtilListener listener) {
        exitToLeft(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void exitToLeft(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setX(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .x(-1500f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.GONE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void exitToTop(final View view, int delay, long duration, final AnimationUtilListener listener) {
        exitToTop(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void exitToTop(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setY(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .y(-1000f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.GONE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void exitAnimateScaleY(final View view, int delay, long duration, final AnimationUtilListener listener) {
        exitAnimateScaleY(view, delay, duration, new FastOutSlowInInterpolator(), listener);
    }

    public static void exitAnimateScaleY(final View view, int delay, long duration, Interpolator interpolator, final AnimationUtilListener listener) {
        view.setScaleX(1f);
        view.setScaleY(1f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .scaleX(1f)
                .scaleY(0f)
                .setInterpolator(interpolator)
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.GONE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public static void animateFadeIn(final View view, int delay, long duration, final AnimationUtilListener listener) {
        view.setAlpha(0f);
        view.animate()
                .alpha(1f)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) {
                            listener.onAnimationEnd();
                        }
                    }

                    @Override public void onAnimationEnd(Animator animator) {

                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();

    }

    public static void animateFadeOut(final View view, int delay, long duration) {
        view.setAlpha(1f);
        view.animate()
                .alpha(0f)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setStartDelay(delay)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animator) {

                    }

                    @Override public void onAnimationEnd(Animator animator) {
                        view.setVisibility(View.GONE);
                    }

                    @Override public void onAnimationCancel(Animator animator) {

                    }

                    @Override public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void enterCircularReveal(View v) {
        int cx = v.getMeasuredWidth() / 2;
        int cy = v.getMeasuredHeight() / 2;

        int finalRadius = Math.max(v.getWidth(), v.getHeight()) / 2;
        Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
        v.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void exitCircularReveal(final View v) {
        int cx = v.getMeasuredWidth() / 2;
        int cy = v.getMeasuredHeight() / 2;

        int initialRadius = v.getWidth() / 2;
        Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, initialRadius, 0);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

    public interface AnimationUtilListener {
        void onAnimationEnd();
    }
}

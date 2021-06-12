package baseproject.tran.canhdinh.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import baseproject.tran.canhdinh.R;
import baseproject.tran.canhdinh.api.ApiRequest;
import baseproject.tran.canhdinh.api.ErrorApiResponse;
import baseproject.tran.canhdinh.helper.BusHelper;
import baseproject.tran.canhdinh.widgets.dialog.ProgressDialog;
import baseproject.tran.canhdinh.widgets.dialog.alert.KAlertDialog;
import baseproject.tran.canhdinh.widgets.progresswindow.kprogresshud.KProgressHUD;


public abstract class BaseFragment<V extends BaseViewInterface, P extends BaseParameters> extends Fragment {
    private ProgressDialog mProgressDialog;
    protected V view;
    protected P parameters;
    private Activity activity;
    private Toast toast;
    protected Handler handler = new Handler();

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = getViewInstance();

        this.parameters = getParametersContainer();

        if (this.parameters != null) {
            this.parameters.bind(this);
        }

        return this.view.inflate(inflater, container);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // no call for super(). Bug on API Level > 11.
    }

    @Override
    public final void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialize();

        BusHelper.register(this);

    }

    protected abstract void initialize();

    protected abstract V getViewInstance();

    protected abstract P getParametersContainer();

    protected void finish() {
        getActivity().finish();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusHelper.unregister(this);
    }

    @Override
    public Context getContext() {
        return activity;
    }

    private boolean hasParameter(String key) {
        return ((getArguments() != null) && getArguments().containsKey(key));
    }

    public void setParameters(P param) {
        Bundle args = param.bundle();
        setArguments(args);
    }

    @SuppressWarnings("unchecked")
    public <T> T getParameter(String key, T defaultValue) {
        if (hasParameter(key)) {
            return (T) getArguments().get(key);
        } else {
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T getParent(Class<T> parentClass) {
        Fragment parentFragment = getParentFragment();

        if (parentClass.isInstance(parentFragment)) {
            return (T) parentFragment;
        } else if (parentClass.isInstance(getActivity())) {
            return (T) getActivity();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    protected void addFragment(BaseFragment<?, ?> fragment, boolean addToBackStack) {
        BaseFragmentActivity<?, ?, ?> baseFragmentActivity = getParent(BaseFragmentActivity.class);

        if (baseFragmentActivity != null) {
            baseFragmentActivity.addFragment(fragment, addToBackStack);
        }
    }

    protected void replaceFragment(BaseFragment<?, ?> fragment, boolean addToBackStack) {
        BaseFragmentActivity<?, ?, ?> baseFragmentActivity = getParent(BaseFragmentActivity.class);

        if (baseFragmentActivity != null) {
            baseFragmentActivity.replaceFragment(fragment, addToBackStack);
        }
    }

    private KProgressHUD hud;

    public void showProgress() {
        showProgress("");
    }

    public void showProgress(@StringRes int resId) {
        showProgress(getString(resId));
    }

    public void showProgress(String message) {
        showProgress(message, true);
    }

    public synchronized void showProgress(String message, boolean timeout) {
        if (hud != null && hud.isShowing()) {

            hud.dismiss();
            hud = null;
        }

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        if (!TextUtils.isEmpty(message)) {
            hud.setLabel(message);
            hud.setCancellable(false);
        }

        hud.show();

        if (timeout) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {

                        hud.dismiss();
                        hud = null;
                    }
                }
            }, 10000);
        }
    }

    public void showProgressWithOutBg() {
        showProgressWithOutBg("", true);
    }

    public synchronized void showProgressWithOutBg(String message, boolean timeout) {
        if (hud != null && hud.isShowing()) {

            hud.dismiss();
            hud = null;
        }

        hud = KProgressHUD.create(getContext())
                .setProgressWithOutBg();
        if (!TextUtils.isEmpty(message)) {
            hud.setLabel(message);
            hud.setCancellable(false);
        }

        hud.show();

        if (timeout) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {

                        hud.dismiss();
                        hud = null;
                    }
                }
            }, 10000);
        }
    }

    public void showFishSpinnerProgress() {
        showFishSpinnerProgress("", true);
    }

    public synchronized void showFishSpinnerProgress(String message, boolean timeout) {
        if (hud != null && hud.isShowing()) {

            hud.dismiss();
            hud = null;
        }

        hud = KProgressHUD.create(getContext())
                .setWindowColor(Color.parseColor("#00000000"))
                .setCustomView();
        if (!TextUtils.isEmpty(message)) {
            hud.setLabel(message);
            hud.setCancellable(false);
        }

        hud.show();

        if (timeout) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {

                        hud.dismiss();
                        hud = null;
                    }
                }
            }, 10000);
        }
    }

    public synchronized void dismissProgress() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (hud != null)
                    hud.dismiss();
            }
        });
    }


    public void showSnackbar(@StringRes int resId) {
        showSnackbar(getString(resId));
    }

    public void showSnackbar(String message) {
        showSnackbar(message, false);
    }

    public void showSnackbar(String message, boolean warning) {
        showSnackbar(message, warning, null, null);
    }

    public void showSnackbar(ErrorApiResponse error) {
        showSnackbar(error.message, true);
    }

    public void showSnackbar(ErrorApiResponse error, OnClickListener actionCallback) {
        showSnackbar(error.message, true, getString(R.string.try_againt), actionCallback);
    }

    public void showSnackbar(ApiRequest.RequestError requestError) {
        showSnackbar(getErrorString(requestError), true);
    }

    public void showSnackbar(ApiRequest.RequestError requestError, OnClickListener actionCallback) {
        showSnackbar(getErrorString(requestError), true, getString(R.string.try_againt), actionCallback);
    }

    public void showSnackbar(@StringRes int resId, boolean warning) {
        showSnackbar(getString(resId), warning);
    }

    public void showSnackbar(String message, boolean warning, String action, OnClickListener actionCallback) {
        Snackbar snackbar = Snackbar.make(view.getView(), message, Snackbar.LENGTH_SHORT);
        if (warning) {
            if (!TextUtils.isEmpty(action) && actionCallback != null) {
                snackbar.setAction(action, actionCallback);
                // Changing message text color
                snackbar.setActionTextColor(Color.RED);
            }

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
        }
        snackbar.show();
    }

    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    public void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        if (toast.getView().getWindowVisibility() == View.VISIBLE) {
            toast.cancel();
        }
        toast.show();
    }

    private KAlertDialog mCustomAlert;

    public void showAlert(String title, String message, int type) {

        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.showCancelButton(false);

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());
        mCustomAlert.setContentText(Html.fromHtml(message).toString());

        mCustomAlert
                .setConfirmText("OK")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        if (mCustomAlert != null)
                            mCustomAlert.dismiss();
                    }
                })
                .changeAlertType(type);
        mCustomAlert.show();
    }

    public void showAlert(String message) {
        showAlert("", message, 0);
    }

    public void showAlert(String message, int type) {
        showAlert("", message, type);
    }

    public void showProgressAlert(String title, String mess) {

        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.showCancelButton(false);

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());
        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        mCustomAlert.changeAlertType(KAlertDialog.PROGRESS_TYPE);

        mCustomAlert.setCancelable(false);
        mCustomAlert.setCanceledOnTouchOutside(false);
        mCustomAlert.setConfirmClickListener(null);
        mCustomAlert.setCancelClickListener(null);
        mCustomAlert.show();
    }

    public void showConfirmAlert(String title, String mess, KAlertDialog.KAlertClickListener actionConfirm, int type) {
        showConfirmAlert(title, mess, actionConfirm, null, type);
    }

    public void showConfirmAlert(String title, String mess, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int type) {
        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCanceledOnTouchOutside(false);
            mCustomAlert.setCancelable(false);
        }
        mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());

        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        if (type >= 0) {
            mCustomAlert.changeAlertType(type);
        } else {
            mCustomAlert.changeAlertType(KAlertDialog.WARNING_TYPE);
        }

        if (actionCancel != null) {
            mCustomAlert.setCancelText(getString(R.string.KAlert_cancel_button_text));
            mCustomAlert.setCancelClickListener(actionCancel);
        } else {
            mCustomAlert.showCancelButton(false);
        }
        if (actionConfirm != null) {
            mCustomAlert.setConfirmClickListener(actionConfirm);
        } else {
            mCustomAlert.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mCustomAlert.dismiss();
                }
            });
        }
        mCustomAlert.show();
    }

    public void showCustomerImageConfirmAlert(String title, String mess, String titleButtonConfirm, String titleButtonCancel, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int resourceId) {
        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());

        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        if (!TextUtils.isEmpty(titleButtonConfirm)) {
            mCustomAlert.setConfirmText(titleButtonConfirm);
        } else {
            mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));
        }

        mCustomAlert.setCustomImage(resourceId);

        mCustomAlert.changeAlertType(KAlertDialog.CUSTOM_IMAGE_TYPE);

        if (actionCancel != null) {
            mCustomAlert.setCancelClickListener(actionCancel);

            if (!TextUtils.isEmpty(titleButtonCancel)) {
                mCustomAlert.setCancelText(titleButtonCancel);
            } else {
                mCustomAlert.setCancelText(getString(R.string.KAlert_cancel_button_text));
            }
        } else {
            mCustomAlert.showCancelButton(false);
        }
        if (actionConfirm != null) {
            mCustomAlert.setConfirmClickListener(actionConfirm);
        } else {
            mCustomAlert.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mCustomAlert.dismiss();
                }
            });
        }
        mCustomAlert.show();
    }

    public void showCustomerImageAndBgButtonConfirmAlert(String title, String mess, String titleButtonConfirm, int bg_button_confirm, String titleButtonCancel, int bg_button_cancel, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int resource_img) {
        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());

        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        if (!TextUtils.isEmpty(titleButtonConfirm)) {
            mCustomAlert.setConfirmText(titleButtonConfirm);
        } else {
            mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));
        }
        if (bg_button_confirm != 0)
            mCustomAlert.setConfirmButtonBgColor(bg_button_confirm);

        mCustomAlert.setCustomImage(resource_img);

        mCustomAlert.changeAlertType(KAlertDialog.CUSTOM_IMAGE_TYPE);

        if (actionCancel != null) {
            mCustomAlert.setCancelClickListener(actionCancel);

            if (!TextUtils.isEmpty(titleButtonCancel)) {
                mCustomAlert.setCancelText(titleButtonCancel);
            } else {
                mCustomAlert.setCancelText(getString(R.string.KAlert_cancel_button_text));
            }

            if (bg_button_cancel != 0)
                mCustomAlert.setCancelButtonBgColor(bg_button_cancel);
        } else {
            mCustomAlert.showCancelButton(false);
        }
        if (actionConfirm != null) {
            mCustomAlert.setConfirmClickListener(actionConfirm);
        } else {
            mCustomAlert.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mCustomAlert.dismiss();
                }
            });
        }
        mCustomAlert.show();
    }

    public void showCustomerBgButtonConfirmAlert(String title, String mess, String titleButtonConfirm, int bg_button_confirm, String titleButtonCancel, int bg_button_cancel, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int type) {
        if (mCustomAlert == null) {
            mCustomAlert = new KAlertDialog(activity);
            mCustomAlert.setCancelable(false);
            mCustomAlert.setCanceledOnTouchOutside(false);
        }
        mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));

        mCustomAlert.setTitleText(Html.fromHtml(title).toString());

        mCustomAlert.setContentText(Html.fromHtml(mess).toString());

        if (!TextUtils.isEmpty(titleButtonConfirm)) {
            mCustomAlert.setConfirmText(titleButtonConfirm);
        } else {
            mCustomAlert.setConfirmText(getString(R.string.KAlert_confirm_button_text));
        }
        if (bg_button_confirm != 0)
            mCustomAlert.setConfirmButtonBgColor(bg_button_confirm);

        if (type >= 0) {
            mCustomAlert.changeAlertType(type);
        } else {
            mCustomAlert.changeAlertType(KAlertDialog.WARNING_TYPE);
        }

        if (actionCancel != null) {
            mCustomAlert.setCancelClickListener(actionCancel);

            if (!TextUtils.isEmpty(titleButtonCancel)) {
                mCustomAlert.setCancelText(titleButtonCancel);
            } else {
                mCustomAlert.setCancelText(getString(R.string.KAlert_cancel_button_text));
            }

            if (bg_button_cancel != 0)
                mCustomAlert.setCancelButtonBgColor(bg_button_cancel);
        } else {
            mCustomAlert.showCancelButton(false);
        }
        if (actionConfirm != null) {
            mCustomAlert.setConfirmClickListener(actionConfirm);
        } else {
            mCustomAlert.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mCustomAlert.dismiss();
                }
            });
        }
        mCustomAlert.show();
    }

    protected String getErrorString(ApiRequest.RequestError requestError) {
        switch (requestError) {
            case ERROR_NETWORK_CANCELLED:
            case ERROR_NETWORK_NO_CONNECTION:
                return getString(R.string.error_connect_internet);
            case ERROR_NETWORK_TIMEOUT:
                return getString(R.string.error_connect_timeout);
            default:
                return getString(R.string.error_other);
        }
    }
}
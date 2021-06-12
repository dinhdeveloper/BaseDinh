package baseproject.tran.canhdinh.dependency;

import android.content.Context;

import java.security.Security;

import baseproject.tran.canhdinh.api.ApiManagement;
import baseproject.tran.canhdinh.helper.AppCleanerHelper;
import baseproject.tran.canhdinh.helper.AuthHelper;
import baseproject.tran.canhdinh.helper.ConnectivityHelper;
import baseproject.tran.canhdinh.helper.FileHelper;
import baseproject.tran.canhdinh.helper.ImageHelper;
import baseproject.tran.canhdinh.helper.InstallationHelper;
import baseproject.tran.canhdinh.helper.LanguageHelper;

public class AppObjectProvider implements ObjectProviderInterface {
    private final Context context;

    // singleton instances
    private InstallationHelper installationHelper;
    private Security security;
    private ImageHelper imageHelper;
    private AppCleanerHelper appCleanerHelper;
    private FileHelper fileHelper;
    private ConnectivityHelper connectivityHelper;
    private ApiManagement apiManagement;
    private LanguageHelper languageHelper;
    private AuthHelper authHelper;

    public AppObjectProvider(Context context) {
        this.context = context;
    }

    @Override
    public ImageHelper getImageHelper() {
        return (imageHelper == null) ? (imageHelper = new ImageHelper(context)) : imageHelper;
    }

    @Override
    public InstallationHelper getInstallationHelper() {
        return (installationHelper == null) ? (installationHelper = new InstallationHelper(context)) : installationHelper;
    }

    @Override
    public AppCleanerHelper getAppCleanerHelper() {
        return (appCleanerHelper == null) ? (appCleanerHelper = new AppCleanerHelper(context, getInstallationHelper())) : appCleanerHelper;
    }

    public FileHelper getFileHelper() {
        return (fileHelper == null) ? (fileHelper = new FileHelper(context)) : fileHelper;
    }

    @Override
    public ConnectivityHelper getConnectivityHelper() {
        return (connectivityHelper == null) ? (connectivityHelper = new ConnectivityHelper(context)) : connectivityHelper;
    }

    public ApiManagement getApiManagement() {
        return (apiManagement == null) ? (apiManagement = new ApiManagement()) : apiManagement;
    }

    @Override
    public LanguageHelper getLanguageHelper() {
        return (languageHelper == null) ? (languageHelper = new LanguageHelper()) : languageHelper;
    }

    @Override
    public AuthHelper getAuthHelper()
    {
        return (authHelper == null) ? (authHelper = new AuthHelper()) : authHelper;
    }
}
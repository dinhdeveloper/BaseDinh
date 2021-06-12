package baseproject.tran.canhdinh.dependency;


import baseproject.tran.canhdinh.api.ApiManagement;
import baseproject.tran.canhdinh.helper.AppCleanerHelper;
import baseproject.tran.canhdinh.helper.AuthHelper;
import baseproject.tran.canhdinh.helper.ConnectivityHelper;
import baseproject.tran.canhdinh.helper.FileHelper;
import baseproject.tran.canhdinh.helper.ImageHelper;
import baseproject.tran.canhdinh.helper.InstallationHelper;
import baseproject.tran.canhdinh.helper.LanguageHelper;

public class AppProvider
{
    private static ObjectProviderInterface instance;

    public static void init(ObjectProviderInterface objectProviderInterface)
    {
        instance = objectProviderInterface;
    }

    public static ImageHelper getImageHelper()
    {
        return instance.getImageHelper();
    }

    public static InstallationHelper getInstallationHelper()
    {
        return instance.getInstallationHelper();
    }

    public static AppCleanerHelper getAppCleanerHelper()
    {
        return instance.getAppCleanerHelper();
    }

    public static FileHelper getFileHelper()
    {
        return instance.getFileHelper();
    }

    public static ConnectivityHelper getConnectivityHelper()
    {
        return instance.getConnectivityHelper();
    }
    public static ApiManagement getApiManagement()
    {
        return instance.getApiManagement();
    }

    public static LanguageHelper getLanguageHelper()
    {
        return instance.getLanguageHelper();
    }

    public static AuthHelper getAuthHelper()
    {
        return instance.getAuthHelper();
    }

}
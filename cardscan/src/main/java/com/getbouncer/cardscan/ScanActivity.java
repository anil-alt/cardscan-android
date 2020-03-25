package com.getbouncer.cardscan;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import androidx.test.espresso.idling.CountingIdlingResource;
import android.text.TextUtils;

import com.getbouncer.cardscan.base.IdleResourceManager;
import com.getbouncer.cardscan.base.ScanActivityImpl;
import com.getbouncer.cardscan.base.ScanBaseActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * The ScanActivity class provides the main interface to the scanning functionality. To use this
 * activity, call the {@link ScanActivity#start} method and override
 * onActivityResult in your own activity to get the result of the scan.
 */
public class ScanActivity {
    private static final String TAG = "ScanActivity";

    private static final int REQUEST_CODE = 51234;
    public static final int RESULT_CANCELED = ScanActivityImpl.RESULT_CANCELED;
    public static final int RESULT_OK = ScanActivityImpl.RESULT_OK;
    public static String RESULT_FATAL_ERROR = ScanBaseActivity.RESULT_FATAL_ERROR;
    public static TestingImageReader testingImageReader = null;
    public static String cameraPermissionTitle;
    public static String cameraPermissionMessage;

    /**
     * Starts a ScanActivityImpl activity, using {@param activity} as a parent.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     */
    public static void start(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted
    ) {
        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param fragment} as a parent.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     */
    public static void start(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted
    ) {
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param activity} as a parent.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     */
    public static void start(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted,
            boolean delayShowingExpiration
    ) {
        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param fragment} as a parent.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     */
    public static void start(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted,
            boolean delayShowingExpiration
    ) {
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param activity} as a parent.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     * @param showEnterCardNumberManually true if the scan activity should show the enter_card_manually button
     */
    public static void start(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted,
            boolean delayShowingExpiration,
            boolean showEnterCardNumberManually
    ) {
        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.SHOW_ENTER_CARD_MANUALLY_BUTTON, showEnterCardNumberManually);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param fragment} as a parent.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     * @param showEnterCardNumberManually true if the scan activity should show the enter_card_manually button
     */
    public static void start(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted,
            boolean delayShowingExpiration,
            boolean showEnterCardNumberManually
    ) {
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.SHOW_ENTER_CARD_MANUALLY_BUTTON, showEnterCardNumberManually);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a scan activity and customizes the text that it displays.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param scanCardText the large text above the card rectangle
     * @param positionCardText the small text below the card rectangle
     */
    public static void start(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted,
            String scanCardText,
            String positionCardText
    ) {

        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.SCAN_CARD_TEXT, scanCardText);
        intent.putExtra(ScanActivityImpl.POSITION_CARD_TEXT, positionCardText);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param fragment} as a parent.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param scanCardText the large text above the card rectangle
     * @param positionCardText the small text below the card rectangle
     */
    public static void start(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted,
            String scanCardText,
            String positionCardText
    ) {
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.SCAN_CARD_TEXT, scanCardText);
        intent.putExtra(ScanActivityImpl.POSITION_CARD_TEXT, positionCardText);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a scan activity and customizes the text that it displays.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param scanCardText the large text above the card rectangle
     * @param positionCardText the small text below the card rectangle
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     * @param showEnterCardNumberManually true if the scan activity should show the enter_card_manually button
     */
    public static void start(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted,
            String scanCardText,
            String positionCardText,
            boolean delayShowingExpiration,
            boolean showEnterCardNumberManually
    ) {
        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.SCAN_CARD_TEXT, scanCardText);
        intent.putExtra(ScanActivityImpl.POSITION_CARD_TEXT, positionCardText);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.SHOW_ENTER_CARD_MANUALLY_BUTTON, showEnterCardNumberManually);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts a ScanActivityImpl activity, using {@param fragment} as a parent.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     * @param apiKey a 32 character API key generated from https://api.getbouncer.com/console
     * @param licenseAccepted true if the implementer has accepted the license for the SDK
     * @param scanCardText the large text above the card rectangle
     * @param positionCardText the small text below the card rectangle
     * @param delayShowingExpiration true if the scan activity should delay showing the expiration
     * @param showEnterCardNumberManually true if the scan activity should show the enter_card_manually button
     */
    public static void start(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted,
            String scanCardText,
            String positionCardText,
            boolean delayShowingExpiration,
            boolean showEnterCardNumberManually
    ) {
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra(ScanActivityImpl.SCAN_CARD_TEXT, scanCardText);
        intent.putExtra(ScanActivityImpl.POSITION_CARD_TEXT, positionCardText);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.SHOW_ENTER_CARD_MANUALLY_BUTTON, showEnterCardNumberManually);
        intent.putExtra(ScanBaseActivity.DELAY_SHOWING_EXPIRATION, delayShowingExpiration);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Initializes the machine learning models and GPU hardware for faster scan performance.
     *
     * This optional static method initializes the machine learning models and GPU hardware in a
     * background thread so that when the ScanActivity starts it can complete its first scan
     * quickly. App builders can choose to not call this method and they can call it multiple
     * times safely.
     *
     * This method is thread safe.
     *
     * @param activity the activity that invokes this method, which the library uses to get
     *                 an application context.
     */
    public static void warmUp(@NotNull Activity activity) {
        ScanBaseActivity.warmUp(activity.getApplicationContext());
    }

    /**
     * Initializes the machine learning models and GPU hardware for faster scan performance.
     *
     * This optional static method initializes the machine learning models and GPU hardware in a
     * background thread so that when the ScanActivity starts it can complete its first scan
     * quickly. App builders can choose to not call this method and they can call it multiple
     * times safely.
     *
     * This method is thread safe.
     *
     * @param fragment the fragment that invokes this method, which the library uses to get
     *                 an application context.
     */
    public static void warmUp(@NotNull Fragment fragment) {
        ScanBaseActivity.warmUp(fragment.getActivity());
    }

    /**
     * Starts the scan activity and turns on a small debugging window in the bottom left.
     *
     * This debugging activity helps designers see some of the machine learning model's internals
     * by showing boxes around digits and expiry dates that it detects.
     *
     * @param activity the parent activity that is waiting for the result of the ScanActivity
     */
    public static void startDebug(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted
    ) {
        startDebug(activity, apiKey, licenseAccepted, null);
    }

    public static void startDebug(
            @NotNull Activity activity,
            @NotNull String apiKey,
            boolean licenseAccepted,
            @Nullable TestingImageReader imageReader
    ) {
        if (imageReader != null) {
            ScanBaseActivity.sTestingImageReader = new TestingImageBridge(imageReader);
        }
        ScanBaseActivity.warmUp(activity.getApplicationContext());
        Intent intent = new Intent(activity, ScanActivityImpl.class);
        intent.putExtra("debug", true);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Starts the scan activity and turns on a small debugging window in the bottom left.
     *
     * This debugging activity helps designers see some of the machine learning model's internals
     * by showing boxes around digits and expiry dates that it detects.
     *
     * @param fragment the parent fragment that is waiting for the result of the ScanActivity
     */
    public static void startDebug(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted
    ) {
        startDebug(fragment, apiKey, licenseAccepted, null);
    }

    public static void startDebug(
            @NotNull Fragment fragment,
            @NotNull String apiKey,
            boolean licenseAccepted,
            @Nullable TestingImageReader imageReader
    ) {
        if (imageReader != null) {
            ScanBaseActivity.sTestingImageReader = new TestingImageBridge(imageReader);
        }
        ScanBaseActivity.warmUp(fragment.getActivity());
        Intent intent = new Intent(fragment.getActivity(), ScanActivityImpl.class);
        intent.putExtra("debug", true);
        intent.putExtra(ScanActivityImpl.API_KEY, apiKey);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_TITLE, cameraPermissionTitle);
        intent.putExtra(ScanActivityImpl.CAMERA_PERMISSION_MESSAGE, cameraPermissionMessage);
        intent.putExtra(ScanActivityImpl.LICENSE_ACCEPTED, licenseAccepted);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * A helper method to use within your onActivityResult method to check if the result is from our
     * scan activity.
     *
     * @param requestCode the requestCode passed into the onActivityResult method
     * @return true if the requestCode matches the requestCode we use for ScanActivity instances
     */
    public static boolean isScanResult(int requestCode) {
        return requestCode == REQUEST_CODE;
    }

    public static @Nullable CreditCard creditCardFromResult(Intent intent) {
        String number = intent.getStringExtra(ScanActivityImpl.RESULT_CARD_NUMBER);
        String month = intent.getStringExtra(ScanActivityImpl.RESULT_EXPIRY_MONTH);
        String year = intent.getStringExtra(ScanActivityImpl.RESULT_EXPIRY_YEAR);

        if (TextUtils.isEmpty(number)) {
            return null;
        }

        return new CreditCard(number, month, year);
    }

    /**
     * Used for getting idle resources that register during card scanning when testing.
     * Only use this as part of your Espresso tests, don't call this for production
     * code.
     */
    public static CountingIdlingResource getScanningIdleResource() {
        return IdleResourceManager.getScanningIdleResource();
    }
}

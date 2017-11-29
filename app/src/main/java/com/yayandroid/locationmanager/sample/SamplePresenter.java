package com.yayandroid.locationmanager.sample;

import android.location.Location;
import android.text.TextUtils;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

public class SamplePresenter {

    private SampleView sampleView;

    public SamplePresenter(SampleView view) {
        this.sampleView = view;
    }

    public void destroy() {
        sampleView = null;
    }

    public void onLocationChanged(Location location) {
        sampleView.dismissProgress();
        setText(location);
    }

    public void onLocationFailed(@FailType int failType) {
        sampleView.dismissProgress();

        switch (failType) {
            case FailType.TIMEOUT: {
                sampleView.setText("No se pudo localizar, timeout!");
                break;
            }
            case FailType.PERMISSION_DENIED: {
                sampleView.setText("No se pudo localizar, Por que no tenemos permiso!");
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                sampleView.setText("No se pudo localizar, por que la red no es accesible!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_NOT_AVAILABLE: {
                sampleView.setText("No se pudo localizar, por que Google Play Services no esta disponible!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_CONNECTION_FAIL: {
                sampleView.setText("No se pudo localizar, por que  falló la conección Google Play Services!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DIALOG: {
                sampleView.setText("No se pudo mostrar settingsApi dialog!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DENIED: {
                sampleView.setText("No se pudo localizar, because user didn't activate providers via settingsApi!");
                break;
            }
            case FailType.VIEW_DETACHED: {
                sampleView.setText("No se pudo localizar, because in the process view was detached!");
                break;
            }
            case FailType.VIEW_NOT_REQUIRED_TYPE: {
                sampleView.setText("No se pudo localizar, "
                      + "because view wasn't sufficient enough to fulfill given configuration!");
                break;
            }
            case FailType.UNKNOWN: {
                sampleView.setText("Ops! Something went wrong!");
                break;
            }
        }
    }

    public void onProcessTypeChanged(@ProcessType int newProcess) {
        switch (newProcess) {
            case ProcessType.GETTING_LOCATION_FROM_GOOGLE_PLAY_SERVICES: {
                sampleView.updateProgress("Getting Location from Google Play Services...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_GPS_PROVIDER: {
                sampleView.updateProgress("Getting Location from GPS...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_NETWORK_PROVIDER: {
                sampleView.updateProgress("Getting Location from Network...");
                break;
            }
            case ProcessType.ASKING_PERMISSIONS:
            case ProcessType.GETTING_LOCATION_FROM_CUSTOM_PROVIDER:
                // Ignored
                break;
        }
    }

    private void setText(Location location) {
        String appendValue = location.getLatitude() + ", " + location.getLongitude() + "\n";
        String newValue;
        CharSequence current = sampleView.getText();

        if (!TextUtils.isEmpty(current)) {
            newValue = current + appendValue;
        } else {
            newValue = appendValue;
        }

        sampleView.setText(newValue);
    }

    public interface SampleView {

        String getText();

        void setText(String text);

        void updateProgress(String text);

        void dismissProgress();

    }

}

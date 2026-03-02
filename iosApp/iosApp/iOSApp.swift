import SwiftUI
import CoreLocation

@main
struct iOSApp: App {

    init() {
        requestLocationPermission()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

func requestLocationPermission() {
    let locationManager = CLLocationManager()
    locationManager.requestWhenInUseAuthorization()
}
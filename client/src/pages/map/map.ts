import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';
import { UserProvider } from '../../providers/user/user';

@IonicPage()
@Component({
    selector: 'page-map',
    templateUrl: 'map.html',
})
export class MapPage {

    @ViewChild('map') mapElement: ElementRef;
    @ViewChild('directionsPanel') directionPanel: ElementRef;
    map: any;
    labels: any;
    labelIndex: any;
    private users: Array<any>;

    constructor(public navCtrl: NavController, public navParams: NavParams, public geoLocation: Geolocation, public userProvider: UserProvider) {
    }

    ionViewDidLoad() {
        this.loadMap();
        this.userProvider.getUsers().subscribe(users => {
            this.users = users;
        })
    }

    showDrivers() {
        for (const user of this.users) {
            if (user.type === 2) {
                let firstName = user.name.substring(0, user.name.indexOf(' '));
                let userLatLng = { lat: user.latitude, lng: user.longitude };
                let marker = new google.maps.Marker({
                    map: this.map,
                    label: firstName,
                    position: userLatLng
                });
                let content = "<h4>" + user.name + "</h4>";
                this.addInfoWindow(marker, content);
            }
        }
        this.map.panTo(this.driverCenter());
        this.map.setZoom(12);
    }

    driverCenter(): google.maps.LatLng {
        let latAvg = 0;
        let longAvg = 0;
        let count = 0;
        for (const user of this.users) {
            if (user.type === 2) {
                count++;
                latAvg += user.latitude;
                longAvg += user.longitude;
            }
        }

        latAvg /= count;
        longAvg /= count;
        return new google.maps.LatLng(latAvg, longAvg);
    }

    loadMap() {
        navigator.geolocation.getCurrentPosition(position => {

            let latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

            const mapOptions = {
                center: latlng,
                zoom: 15,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                streetViewControl: false
            };

            this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

        }, (err) => console.error(err));
    }

    addInfoWindow(marker, content) {

        let infoWindow = new google.maps.InfoWindow({
            content: content
        });

        google.maps.event.addListener(marker, 'click', () => {
            infoWindow.open(this.map, marker);
        });

    }

}
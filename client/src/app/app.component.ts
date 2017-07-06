import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { LoadingController } from 'ionic-angular';


//Pages
import { LoginPage } from '../pages/login/login';
import { TabsPage } from './../pages/tabs/tabs';

//Providers
import { AngularFireAuth } from 'angularfire2/auth';

@Component({
    template: `<ion-nav [root]="rootPage"></ion-nav>`
})
export class MyApp {
    @ViewChild(Nav) nav: Nav;

    rootPage: any;
    loader: any;

    pages: Array<{ title: string, component: any }>;

    constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen, public afAuth: AngularFireAuth,
        public loadingCtrl: LoadingController) {
        this.initializeApp();
        const authObserver = afAuth.authState.subscribe(user => {
            if (user) {
                this.rootPage = TabsPage;
                authObserver.unsubscribe();
            } else {
                this.rootPage = LoginPage;
                authObserver.unsubscribe();
            }
        });

    }

    initializeApp() {
        this.platform.ready().then(() => {
            this.statusBar.styleDefault();
            this.splashScreen.hide();
        });
    }
}


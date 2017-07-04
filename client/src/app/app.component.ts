import { AuthProvider } from './../providers/auth/auth';
import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { TabsPage } from './../pages/tabs/tabs';
import { LoadingController } from 'ionic-angular';
import { AngularFireAuth } from 'angularfire2/auth';
import { LoginPage } from '../pages/login/login';


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

    // used for an example of ngFor and navigation
    // this.pages = [
    //   { title: 'Page One', component: Page1 },
    //   { title: 'Page Two', component: Page2 }
    // ];

  }


  presentLoading() {
    this.loader = this.loadingCtrl.create({
      content: 'Authenticating...'
    });

    this.loader.present();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  // openPage(page) {
  //   // Reset the content nav to have just this page
  //   // we wouldn't want the back button to show in this scenario
  //   this.nav.setRoot(page.component);
  // }
}


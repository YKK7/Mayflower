import { environment } from './../environments/environment';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { AngularFireModule } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { HttpModule } from '@angular/http';

//Pages
import { LoginPage } from './../pages/login/login';
import { SignupPage } from './../pages/signup/signup';
import { ResetPasswordPage } from './../pages/reset-password/reset-password';
import { TabsPage } from './../pages/tabs/tabs';
import { MapPage } from './../pages/map/map';
import { DeliveryPage } from './../pages/delivery/delivery';

//Providers
import { AuthProvider } from '../providers/auth/auth';
import { UserProvider } from '../providers/user/user';
import { Geolocation } from '@ionic-native/geolocation';


@NgModule({
  declarations: [
    MyApp,
    LoginPage,
    SignupPage,
    TabsPage,
    ResetPasswordPage,
    MapPage,
    DeliveryPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp, {}, { links: [] }),
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    HttpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    SignupPage,
    TabsPage,
    ResetPasswordPage,
    MapPage,
    DeliveryPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    { provide: ErrorHandler, useClass: IonicErrorHandler },
    AuthProvider,
    UserProvider,
    Geolocation
  ]
})
export class AppModule { }

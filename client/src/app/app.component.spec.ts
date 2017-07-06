import { async, TestBed } from '@angular/core/testing';
import { IonicModule, Platform } from 'ionic-angular';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { AngularFireAuth } from 'angularfire2/auth';

import { MyApp } from './app.component';
import { PlatformMock, StatusBarMock, SplashScreenMock } from '../../test-config/mocks-ionic';

describe('MyApp Component', () => {
  let fixture;
  let component;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MyApp],
      imports: [
        IonicModule.forRoot(MyApp)
      ],
      providers: [
        { provide: StatusBar, useClass: StatusBarMock },
        { provide: SplashScreen, useClass: SplashScreenMock },
        { provide: Platform, useClass: PlatformMock }
        // ,{ provide: AngularFireAuth, useClass: AngularFireAuthMock}
      ]
    })
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyApp);
    component = fixture.componentInstance;
  });

  xit('should be created', () => {
    expect(component instanceof MyApp).toBe(true);
  });

  xit('should have two pages', () => {
    console.log("component.pages.length" + component.pages.length);
    expect(component.pages.length).toBe(2);
  });

});

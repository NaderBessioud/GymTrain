import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Components/home/home.component';
import { AuthenticationComponent } from './Components/authentication/authentication.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from "@angular/material/dialog";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import { NavandsidebarComponent } from './Components/Widgets/navandsidebar/navandsidebar.component';
import { NavbarComponent } from './Components/Widgets/navbar/navbar.component';
import { UserProfileComponent } from './Components/user-profile/user-profile.component';
import { PhoneNumberDirective } from './Directive/phone-number.directive';
import {MatSelectModule} from "@angular/material/select";
import {TokenInterceptorService} from "./Interceptor/token-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthenticationComponent,
    NavandsidebarComponent,
    NavbarComponent,
    UserProfileComponent,
    PhoneNumberDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    HttpClientModule,
    FormsModule,
    MatSelectModule

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

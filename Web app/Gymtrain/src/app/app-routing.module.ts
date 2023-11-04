import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserProfileComponent} from "./Components/user-profile/user-profile.component";
import {HomeComponent} from "./Components/home/home.component";
import {NavandsidebarComponent} from "./Components/Widgets/navandsidebar/navandsidebar.component";
import {AuthenticationComponent} from "./Components/authentication/authentication.component";
import {WorkoutsComponent} from "./Components/workouts/workouts.component";

const routes: Routes = [

  {path:"home",component:HomeComponent, data: {
      pageTitle: 'IronWave',
    }},
  {
    path: '',
    component: NavandsidebarComponent,
    children: [
      { path: 'profile', component: UserProfileComponent },
      { path: 'Calender', component: WorkoutsComponent },
    ],
  },


];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {


}

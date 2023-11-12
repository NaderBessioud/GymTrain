import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserProfileComponent} from "./Components/user-profile/user-profile.component";
import {HomeComponent} from "./Components/home/home.component";
import {NavandsidebarComponent} from "./Components/Widgets/navandsidebar/navandsidebar.component";
import {AuthenticationComponent} from "./Components/authentication/authentication.component";
import {WorkoutsComponent} from "./Components/workouts/workouts.component";
import {AddWorkoutComponent} from "./Components/add-workout/add-workout.component";
import {ExercicesComponent} from "./Components/exercices/exercices.component";
import {NutritionComponent} from "./Components/nutrition/nutrition.component";
import {ContactComponent} from "./Components/contact/contact.component";
import {AcceuilComponent} from "./Components/acceuil/acceuil.component";

const routes: Routes = [
  {path:"home",component:HomeComponent, data: {
      title: 'IronWave',
    }},
  {
    path: 'ironwave',
    component: NavandsidebarComponent,
    children: [
      { path: 'profile', component: UserProfileComponent,data:{
          title:"Profile"
        } },
      { path: 'Calender', component: WorkoutsComponent,data:{
          title:"Workout Calender"
        } },
      { path: 'NewWorkout', component: AddWorkoutComponent,data:{
          title:"Workouts"
        } },
      { path: 'Exercices', component: ExercicesComponent,data:{
          title:"Exercices"
        } },
      { path: 'Nutrition', component: NutritionComponent,data:{
          title:"Nutrition"
        } },
      { path: 'Contact', component: ContactComponent,data:{
          title:"Contact us"
        } },
      { path: 'WelcomePage', component: AcceuilComponent,data:{
          title:"Iron Wave"
        } },
    ],
  },
  { path: '**', redirectTo: sessionStorage.getItem("id") ? '/ironwave/WelcomePage' : '/home', pathMatch: 'full' },



];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {


}

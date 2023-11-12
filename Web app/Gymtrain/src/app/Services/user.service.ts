import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {User} from "../Models/user";
import {Observable} from "rxjs";
import {ImageResponse} from "../Models/image-response";

import {Workoutevent} from "../Models/workoutevent";
import {Workout} from "../Models/workout";
import {Exercice} from "../Models/exercice";
import {Nutrition} from "../Models/nutrition";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  URL = "http://localhost:8082/Gym/api/user/"
  constructor(private http:HttpClient) { }


  UploadImage(image:FormData):Observable<ImageResponse>{
    return this.http.post<ImageResponse>(this.URL+ "uploadimage",image);
  }

  DownloadImage(name:string):Observable<ImageResponse>{
    return this.http.get<ImageResponse>(this.URL + "downloadimage",{params:{image:name}})
  }
  UpdateProfile(user:User):Observable<User>{
    return this.http.post<User>(this.URL+"updateProfile",user);
  }

  UpdatePassword(cpass:string, id:any):Observable<boolean>{
    return this.http.get<boolean>(this.URL+"updatePassword",{params:{pass:cpass,id:id}})
  }

  CheckCurrentPasswodr(cpass:string,id:any):Observable<boolean>{
    return this.http.get<boolean>(this.URL+"checkpass",{params:{pass:cpass,id:id}})
  }

  GetUserById(id:any):Observable<User>{
    return this.http.get<User>(this.URL+"userPerId",{params:{id:id}})
  }
  updateWorkoutRoutine(u:User):Observable<User>{
    return this.http.post<User>(this.URL+"upadateWorkoutRoutine",u);
  }

  getWorkoutsDate(id:any):Observable<Workoutevent[]>{
    return this.http.get<Workoutevent[]>(this.URL+"WorkoutsDate",{params:{id:id}})
  }

  addWorkout(workout:Workout,id:any):Observable<Workout>{
    return this.http.post<Workout>(this.URL+"addWorkout",workout,{params:{id:id}})
  }



  getExerciceById(id:any):Observable<Exercice>{
    return this.http.get<Exercice>(this.URL+"getExerciceById",{params:{id:id}})
  }
  updateExercice(ex:Exercice):Observable<Exercice>{
    return this.http.put<Exercice>(this.URL+"updateExercice",ex)
  }

  getAllExercicesByUser(id:any):Observable<Exercice[]>{
    return this.http.get<Exercice[]>(this.URL+"getAllExercicesByUser",{params:{id:id}})
  }

  getAllExercicesByUserAndMuscle(id:any,label:any):Observable<Exercice[]>{
    return this.http.get<Exercice[]>(this.URL+"getAllExercicesByUserAndMuscle",{params:{id:id,label:label}})
  }

  getUserNutrition(id:any,percentage:number,nb:number):Observable<Nutrition>{
    return this.http.get<Nutrition>(this.URL+"getUserNutrition",{params:{id:id,percentage:percentage,nb:nb}})
  }

}




import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {JwtAuthenticationResponse} from "../Models/jwt-authentication-response";
import {Observable} from "rxjs";
import {User} from "../Models/user";
import {ImageResponse} from "../Models/image-response";
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  URL = "http://localhost:8082/Gym/api/auth/"
  constructor(private http:HttpClient,private router:Router) { }

  Login(email:string,pass:string):Observable<JwtAuthenticationResponse>{
    return this.http.get<JwtAuthenticationResponse>(this.URL+"signin",{params:{email:email,password:pass}})
  }

  Register(user:User):Observable<User>{
    return this.http.post<User>(this.URL+"signup",user);
  }

  Checkemail(email:string):Observable<boolean>{
    return this.http.get<boolean>(this.URL+"checkemail",{params:{email:email}})
  }

  signout(cause:string){
    sessionStorage.removeItem("id")
    sessionStorage.removeItem("token")
    sessionStorage.removeItem("email")
    console.log(cause)
    this.router.navigate(['home'], { state: { source: cause } })
  }




}





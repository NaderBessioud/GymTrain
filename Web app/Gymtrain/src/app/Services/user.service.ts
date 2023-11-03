import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {User} from "../Models/user";
import {Observable} from "rxjs";
import {ImageResponse} from "../Models/image-response";

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

}




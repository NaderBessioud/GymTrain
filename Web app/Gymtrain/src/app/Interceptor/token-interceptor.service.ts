import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {AuthenticationService} from "../Services/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authService:AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token=sessionStorage.getItem("token");
    if ( token) {

      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer '+ token,
        },
      });
    }
    return next.handle(req).pipe(catchError(err => {
      const  error=err.error.message ||  err.statusText;
      if(err.status==401){
        this.authService.signout("unauthorized")


      }
      else if(err.status==403){
        if(error=="Token expired"){
          this.authService.signout("tokenexpired")


        }

      }



      return throwError(error);
    }))
  }

}

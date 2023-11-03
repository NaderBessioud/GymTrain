import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SideBarService {
  private sidebarOpenSubject = new BehaviorSubject<boolean>(false);
  constructor() { }

  setSidebarOpen(open: boolean) {
    this.sidebarOpenSubject.next(open);
  }

  getSidebarOpen(): Observable<boolean> {
    return this.sidebarOpenSubject.asObservable();
  }
}

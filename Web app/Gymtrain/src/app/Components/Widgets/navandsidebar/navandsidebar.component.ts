import { Component, OnInit, Renderer2, ElementRef, } from '@angular/core';
import {SideBarService} from "../../../Services/side-bar.service";
import {UserProfileComponent} from "../../user-profile/user-profile.component";
import {AuthenticationComponent} from "../../authentication/authentication.component";



@Component({
  selector: 'app-navandsidebar',
  templateUrl: './navandsidebar.component.html',
  styleUrls: ['./navandsidebar.component.scss']
})
export class NavandsidebarComponent implements OnInit {

  sidebarOpen: boolean=true;
  currentComponent: any = null;
  constructor(private renderer: Renderer2, private el: ElementRef,
              private sidebarService: SideBarService) {
    this.sidebarService.getSidebarOpen().subscribe((open) => {
      this.sidebarOpen = open;
    });
  }
  isDropdownOpen: boolean = false;

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  ngOnInit(): void {
    const toggle = this.el.nativeElement.querySelector('#header-toggle');
    const nav = this.el.nativeElement.querySelector('#nav-bar');
    const bodypd = this.el.nativeElement.querySelector('#body-pd');
    const headerpd = this.el.nativeElement.querySelector('#header');
    const linkColor = this.el.nativeElement.querySelectorAll('.nav_link') as HTMLAnchorElement[];

    if (toggle && nav && bodypd && headerpd) {

      this.renderer.listen(toggle, 'click', () => {
        this.sidebarOpen=!this.sidebarOpen;
        if (nav.classList.contains('show')) {
          // Navbar is open, so we should close it
          this.renderer.removeClass(nav, 'show');
          this.renderer.removeClass(toggle, 'bx-x');
          this.renderer.removeClass(bodypd, 'body-pd');

        } else {
          // Navbar is closed, so we should open it
          this.renderer.addClass(nav, 'show');
          this.renderer.addClass(toggle, 'bx-x');
          this.renderer.addClass(bodypd, 'body-pd');

        }
      });
    }


    linkColor.forEach((link: HTMLAnchorElement) => {

      this.renderer.listen(link, 'click', () => {
        console.log("ttttt")
        linkColor.forEach((l: HTMLAnchorElement) => this.renderer.removeClass(l, 'active'));
        this.renderer.addClass(link, 'active');
      });
    });
  }
  loadComponent(component: string) {
    switch (component) {
      case 'dashboard':
        this.currentComponent = UserProfileComponent;
        break;
      case 'profile':
        this.currentComponent = UserProfileComponent;
        break;
      case 'settings':
        this.currentComponent = AuthenticationComponent;
        break;
    }
  }

}

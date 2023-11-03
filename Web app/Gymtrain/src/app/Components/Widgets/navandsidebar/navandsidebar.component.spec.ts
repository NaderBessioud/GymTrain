import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavandsidebarComponent } from './navandsidebar.component';

describe('NavandsidebarComponent', () => {
  let component: NavandsidebarComponent;
  let fixture: ComponentFixture<NavandsidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavandsidebarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavandsidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

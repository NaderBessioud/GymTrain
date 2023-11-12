import { Component, OnInit, ViewChild } from '@angular/core';
import {UserService} from "../../Services/user.service";
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import {Workoutevent} from "../../Models/workoutevent";
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../Models/user";
import {Router} from "@angular/router";


@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrls: ['./workouts.component.scss']
})
export class WorkoutsComponent implements OnInit {
  modalRef?:BsModalRef
  title:string=""
  start:any
  WorkoutEvents:Workoutevent[]=[]
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin],

    eventClick : this.handleDateClick.bind(this),
    displayEventTime: false,
  };
  good:boolean=false
  dismissible = true;
  alert:any =
    {
      type: '',
      msg: ``
    }

  config= {
    animated :true
  }
  form: FormGroup=new FormBuilder().group({});
  ppr:string="0"
  plr:string="0"
  lpr:string="0"

  @ViewChild('template') template!: string
  constructor(private service:UserService, private modelService: BsModalService,
              private fb:FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
    if(!sessionStorage.getItem("id")){
      this.router.navigate(['home'], { state: { source: 'inside' } })
    }
    this.updateEvent()

    this.form = this.fb.group({
      ppr: ['', [Validators.required]],
      plr: ['', [Validators.required]],
      lpr: ['', [Validators.required]],
    })
  }

  handleDateClick(arg:any){
  this.title = arg.event._def.title;
  this.start = arg.event.start
    this.modalRef = this.modelService.show(this.template, this.config)
  }

  updateWorkoutRoutine(){
    var user:User=new User();
    user.idu=sessionStorage.getItem("id")
    user.workoutroutine=this.ppr+"-"+this.plr+"-"+this.lpr
    this.service.updateWorkoutRoutine(user).subscribe(r=>{
      this.updateEvent()
      this.alert.msg="Workout routine updated successfuly"
      this.alert.type="success"
    })
  }

  updateEvent(){
    this.service.getWorkoutsDate(sessionStorage.getItem("id")).subscribe(events=> {
      if(events != null){
        this.WorkoutEvents = events
        this.calendarOptions.events = this.WorkoutEvents
      }
      else{
        alert("Add a workout or check your workout routine")
      }


    })
  }

  closeAlert(){
    this.good=false
  }

}

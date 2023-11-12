import { Component, OnInit } from '@angular/core';
import {Nutrition} from "../../Models/nutrition";
import {UserService} from "../../Services/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../Models/user";
import {UpdateExerciceComponent} from "../update-exercice/update-exercice.component";
import {MatDialog} from "@angular/material/dialog";
import {NutritionDetailsComponent} from "../nutrition-details/nutrition-details.component";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-nutrition',
  templateUrl: './nutrition.component.html',
  styleUrls: ['./nutrition.component.scss']
})
export class NutritionComponent implements OnInit {
  UserNutrition:Nutrition=new Nutrition()
  NutritionForm: FormGroup=new FormBuilder().group({});
  percentage:any;
  gender:string=""
  nbexp:any;
  nb:number=0;
  pers:number=0
  exps:any=["Less then 6 months","Over 2 years","Over 3 years"]
  constructor(private service:UserService,private fb: FormBuilder,
              public dialog: MatDialog,
              private router:Router) { }

  ngOnInit(): void {
    if(!sessionStorage.getItem("id")){
      this.router.navigate(['home'], { state: { source: 'inside' } })
    }
    this.NutritionForm = this.fb.group({
      perc: ['', Validators.required],
      exp: ['', Validators.required],
    });

    this.service.GetUserById(sessionStorage.getItem("id")).subscribe(us=>{
      this.gender=us.gender
    })
  }

  getUserNutrition(){
    if(this.nbexp =="Less then 6 months"){
      this.nb=1
    }
    else if(this.nbexp == "Over 2 years"){
      this.nb=1.2
    }
    else{
      this.nb=1.5
    }

    switch (this.percentage){
      case 1:
      case 2:
      case 3:
        this.pers=15
        break
      case 4:
        this.pers=14;
        break
      default:this.pers=13
    }
    this.service.getUserNutrition(sessionStorage.getItem("id"),this.pers,this.nb).subscribe(nu=>{
      this.UserNutrition=nu;
      console.log(nu)



    if (this.dialog.openDialogs.length > 0) {

      this.dialog.closeAll();
      const dialogRef = this.dialog.open(NutritionDetailsComponent, {

        width:"350px",
        height:"200px",
        panelClass: 'custom-dialog-panel',

        data: this.UserNutrition,


      },);

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });

    } else {
      const dialogRef = this.dialog.open(NutritionDetailsComponent, {


        width:"350px",
        height:"200px",
        panelClass: 'custom-dialog-panel',
        data: this.UserNutrition,

      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });
    }
    })
  }


}

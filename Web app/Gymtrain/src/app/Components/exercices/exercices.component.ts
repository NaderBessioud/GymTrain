import { Component, OnInit } from '@angular/core';
import {UserService} from "../../Services/user.service";
import {Exercice} from "../../Models/exercice";
import {MatDialog} from "@angular/material/dialog";

import {UpdateExerciceComponent} from "../update-exercice/update-exercice.component";
import {DataService} from "../../Services/data.service";
import {ex} from "@fullcalendar/core/internal-common";
import {Router} from "@angular/router";


@Component({
  selector: 'app-exercices',
  templateUrl: './exercices.component.html',
  styleUrls: ['./exercices.component.scss']
})
export class ExercicesComponent implements OnInit {
  exerices:Exercice[]=[]
  BodyParts:any
  Muscles:any


  showExercice:boolean=false
  imageSources: string[] = [
    'assets/images/banner2.jpg',
    'assets/images/banner2.jpg',
    'assets/images/banner2.jpg',
    'assets/images/banner2.jpg',
    'assets/images/banner2.jpg',
    'assets/images/banner2.jpg',
    // Add more image sources as needed
  ];
  jsonData: any;
  selectedBodyPart:any=[];
  initialColumns: number = 2;
  columnsToShow: number = this.initialColumns;
  // Add more body parts and their associated items as needed

  constructor(private service:UserService,public dialog: MatDialog,
              private dataService:DataService,
              private router:Router) { }


  ngOnInit(): void {
    if(!sessionStorage.getItem("id")){
      this.router.navigate(['home'], { state: { source: 'inside' } })
    }
  this.dataService.getJsonData().subscribe(data=>{
    this.jsonData = data
    this.BodyParts=data.bodyParts
  })




  }


  showMoreColumns() {
    this.columnsToShow += 2;
  }

  updateSelectedBodyPart(part: any) {


    const selectedBodyPartData = this.jsonData.bodyParts.find((bodyPart:any) => bodyPart.label === part.label);
    if (selectedBodyPartData) {
      // Access the muscles of the selected body part
       this.Muscles = selectedBodyPartData.muscles;
       for(let i of this.Muscles){

       }

    }

  }



  openModal(id:any) {
    if (this.dialog.openDialogs.length > 0) {

      this.dialog.closeAll();
      const dialogRef = this.dialog.open(UpdateExerciceComponent, {

        width:"350px",
        height:"350px",
        panelClass: 'custom-dialog-panel',

        data: id,


      },);

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });

    } else {
      const dialogRef = this.dialog.open(UpdateExerciceComponent, {


        width:"350px",
        height:"350px",
        panelClass: 'custom-dialog-panel',
        data: id,

      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });
    }



  }

  getExercicesByMuscle(item:any){
    this.service.getAllExercicesByUserAndMuscle(sessionStorage.getItem("id"),item.label).subscribe(exs=>{

      this.exerices=exs
      for(let i=0;i<this.exerices.length;i++){
        this.service.DownloadImage(this.exerices[i].image).subscribe(image=>{
          this.exerices[i].image=image

        })
      }
      console.log(this.exerices)
      this.showExercice=true

    })
  }

}

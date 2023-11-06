import { Component, OnInit } from '@angular/core';
import {Workout} from "../../Models/workout";
import {UserService} from "../../Services/user.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Exercice} from "../../Models/exercice";
import {ex} from "@fullcalendar/core/internal-common";

@Component({
  selector: 'app-add-workout',
  templateUrl: './add-workout.component.html',
  styleUrls: ['./add-workout.component.scss']
})
export class AddWorkoutComponent implements OnInit {
  workout:Workout=new Workout()
  types:any=["Push","Pull","Legs"]
  exs:Exercice[]=[]
  BodyParts:string[]=[]
  Muscles:string[]=[]
  ExercicesByMuscle:string[]=[]
  workoutForm: FormGroup=new FormBuilder().group({});
  exerciceForm: FormGroup=new FormBuilder().group({});
  exercises: FormArray=new FormBuilder().array([]);
  currentFieldsetIndex: number = 0;
  currentDate: Date = new Date()
  constructor(private service:UserService,private fb: FormBuilder) {

  }
  getexercises(): FormArray {
    return this.workoutForm.get('exercises') as FormArray;
  }

  ngOnInit(): void {

    this.LoadBodyPart()
    this.workoutForm = this.fb.group({
      title: ['', Validators.required],
      type: ['', Validators.required],
      date: ['', Validators.required],

    });

    this.exerciceForm = this.fb.group({
      label: ['', Validators.required],
      bodypart: ['', Validators.required],
        sets: ['', Validators.required],
        rep: ['', Validators.required],
        muscle: ['', Validators.required],
        resistance: ['', Validators.required],

    });
    this.exs.push(new Exercice())
  }



  addWorkout(){
    this.service.addWorkout(this.workout,sessionStorage.getItem("id")).subscribe(w=>{
        if(w != null){
          alert("Workout added Successfuly")
          this.workout=new Workout();
          this.exs.splice(1)
          this.exs[0]=new Exercice();
        }
    })
  }
  createExerciseGroup(i:any) {
    const exerciseGroupName = 'exercise' + i; // Concatenate i with a prefix
    this.exerciceForm.addControl("label"+i,this.fb.control('', Validators.required))
    this.exerciceForm.addControl("bodypart"+i,this.fb.control('', Validators.required))
    this.exerciceForm.addControl("sets"+i,this.fb.control('', Validators.required))
    this.exerciceForm.addControl("rep"+i,this.fb.control('', Validators.required))
    this.exerciceForm.addControl("muscle"+i,this.fb.control('', Validators.required))
    this.exerciceForm.addControl("resistance"+i,this.fb.control('', Validators.required))


    return this.exerciceForm

  }

  addExercise() {

    this.exs.push(new Exercice())

    this.exercises.push(this.createExerciseGroup(this.exs.length-2));

  }

  removeExercise(index: number) {
    this.exs.splice(index+1,1)
    this.exercises.removeAt(index);
  }

  submitWorkout() {
    this.workout.exercices=this.exs
    this.addWorkout()

  }

  LoadBodyPart(){
    this.service.LoadBodyPart().subscribe(parts=>{
      this.BodyParts=parts
    })
  }

  LoadMusclesByBodyPart(event:any){
    this.service.LoaMuscleBydBodyPart(event.source.value).subscribe(muscles=>{
      this.Muscles=muscles;
    })
  }

  LoadExercicesByMuscle(event:any){
    this.service.LoadExercicesByMuscle(event.source.value).subscribe(exercices=>{
      this.ExercicesByMuscle=exercices
    })
  }

}

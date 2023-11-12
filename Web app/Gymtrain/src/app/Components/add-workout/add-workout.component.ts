import {Component, OnInit, Renderer2} from '@angular/core';
import {Workout} from "../../Models/workout";
import {UserService} from "../../Services/user.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Exercice} from "../../Models/exercice";
import {ex} from "@fullcalendar/core/internal-common";
import {DataService} from "../../Services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-workout',
  templateUrl: './add-workout.component.html',
  styleUrls: ['./add-workout.component.scss']
})
export class AddWorkoutComponent implements OnInit {
  workout:Workout=new Workout()
  types:any=["Push","Pull","Legs"]
  exs:Exercice[]=[]
  BodyParts:any
  Muscles:{ [key: string]: any[] } = {}
  ExercicesByMuscle:{ [key: string]: { [key: string]: string[] } } = {};
  workoutForm: FormGroup=new FormBuilder().group({});
  exerciceForm: FormGroup=new FormBuilder().group({});
  exercises: FormArray=new FormBuilder().array([]);
  currentFieldsetIndex: number = 0;
  currentDate: Date = new Date()
  jsonData:any
  part:string=""
  bodypart:string=""
  muscle1:any
  good:boolean=false

  dismissible = true;
  constructor(private service:UserService,private fb: FormBuilder,
              private dataService:DataService,
              private renderer: Renderer2,
              private router:Router) {

  }
  getexercises(): FormArray {
    return this.workoutForm.get('exercises') as FormArray;
  }

  ngOnInit(): void {
    if(!sessionStorage.getItem("id")){
      this.router.navigate(['home'], { state: { source: 'inside' } })
    }

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
          this.good=true
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
    const currentBodyHeight = document.body.offsetHeight;
    const newBodyHeight = currentBodyHeight + 200;

    this.renderer.setStyle(document.body, 'height', `${newBodyHeight}px`);
    this.exs.push(new Exercice())

    this.exercises.push(this.createExerciseGroup(this.exs.length-2));

  }

  removeExercise(index: number) {
    const currentBodyHeight = document.body.offsetHeight;
    const newBodyHeight = currentBodyHeight - 200;

    this.renderer.setStyle(document.body, 'height', `${newBodyHeight}px`);
    this.exs.splice(index+1,1)
    this.exercises.removeAt(index);
  }

  submitWorkout() {
    this.workout.exercices=this.exs
    this.addWorkout()

  }

  LoadBodyPart(){
    this.dataService.getJsonData().subscribe(data=>{

      this.jsonData = data
      this.BodyParts=data.bodyParts
      for(const part of this.BodyParts){
        this.ExercicesByMuscle[part.label]={}

        if (!this.ExercicesByMuscle[part.label]) {
          this.ExercicesByMuscle[part.label] = {};

        }
        const selectedBodyPartData = this.jsonData.bodyParts.find((bodyPart:any) => bodyPart.label === part.label);

        this.Muscles[part.label] = selectedBodyPartData.muscles


        for(const m of selectedBodyPartData.muscles){
          if (!this.ExercicesByMuscle[part.label][m.label]) {
            this.ExercicesByMuscle[part.label][m.label] = [];
          }

          const selectedMuscleData = selectedBodyPartData.muscles.find((muscle:any) => muscle.label === m.label);
          this.ExercicesByMuscle[part.label][m.label]=selectedMuscleData.exercises
        }
      }
    })

  }

  LoadMusclesByBodyPart(event:any){

    this.bodypart= event.source.value.label
    const selectedBodyPartData = this.jsonData.bodyParts.find((bodyPart:any) => bodyPart.label === event.source.value.label);
    if (selectedBodyPartData) {
      // Access the muscles of the selected body part
      this.Muscles = selectedBodyPartData.muscles;

    }
  }

  LoadExercicesByMuscle(event:any){

    const selectedBodyPartData = this.jsonData.bodyParts.find((bodyPart:any) => bodyPart.label === this.bodypart );
    if (selectedBodyPartData) {

      // Access the muscles of the selected body part

      const selectedMuscleData = selectedBodyPartData.muscles.find((muscle:any) => muscle.label === event.source.value.label);

      if (selectedMuscleData) {

        this.ExercicesByMuscle=selectedMuscleData.exercises
        console.log(this.ExercicesByMuscle)
      }
    }

  }

  closeAlert(){
    this.good=false
  }

}

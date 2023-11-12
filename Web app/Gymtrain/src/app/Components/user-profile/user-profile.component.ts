import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../Services/authentication.service";
import {User} from "../../Models/user";
import {UserService} from "../../Services/user.service";
import {SideBarService} from "../../Services/side-bar.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {



  isinfotTab: boolean = true;
  form: FormGroup=new FormBuilder().group({});
  formP: FormGroup=new FormBuilder().group({});
  phoneNumberForm: FormGroup=new FormBuilder().group({});
  genders=["men","women"]
  dbImage: any;
  user:User=new User();
  currentDate:Date=new Date()
  cpass:string="";
  npass:string="";
  cnpass:string="";
  visiblePass:boolean=false;
  matchPass:boolean=false;
  PassMatch:string="2 password does not match";
  isButtonDisabled = false
  fullName:string=""
  dismissible = true;
  alert:any =
    {
      type: 'success',
      msg: `Profile updated.`
    }
    good=false


  constructor(private fb:FormBuilder,private renderer: Renderer2,
              private service:UserService,
              private router:Router) { }

    tabContainerWidth: number = 0; // Initialize to 0



  ngOnInit(): void {
    if(!sessionStorage.getItem("id")){
      this.router.navigate(['home'], { state: { source: 'inside' } })
    }

    this.service.GetUserById(sessionStorage.getItem("id")).subscribe(u=>{
      this.user=u
      this.fullName=u.firstName+" "+u.lastName
      this.downloadImage(u.image);
    })

      this.phoneNumberForm = this.fb.group({
          part1: [''],
          part2: [''],
          part3: [''],
      });
    this.form = this.fb.group({
      lastname: ['', [Validators.required]],
      firstname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required]],
      tel: ['', [Validators.required]],
        weight: ['', [Validators.required]],
        height: ['', [Validators.required]],
        aboutMe: ['', [Validators.required]],
        gender: ['', [Validators.required]],
      birthdate: ['', [Validators.required]],
    })

      this.formP = this.fb.group({
          cpass: ['', [Validators.required]],
          npass: ['', [Validators.required]],
          copass: ['', [Validators.required]],
      })


  }

  switchToInfo() {
    this.isinfotTab= true;




  }

  switchToPassword() {

    this.isinfotTab = false;



  }


  onimageChanged(event:Event){

      const fileInput = event.target as HTMLInputElement;
    if(fileInput.files && fileInput.files.length > 0) {
      // Fill file variable with the file content




    const imageFormData = new FormData();
    imageFormData.append('imageFile', fileInput.files[0], fileInput.files[0].name);

    this.service.UploadImage(imageFormData).subscribe(data=>{

      this.downloadImage(data.image);
      this.user.image = data.image
    })
    }

  }

    downloadImage(name:string){

        this.service.DownloadImage(name).subscribe(res=>{
            if(res != null){
              this.dbImage=res.image;
            }
        })
    }

    updateProfile(){
    this.user.idu=sessionStorage.getItem("id");
     var user1:User=new User();
     user1.idu=sessionStorage.getItem("id")
      user1.firstName=this.user.firstName
      user1.lastName=this.user.lastName
      user1.gender=this.user.gender
      user1.phone=this.user.phone
      user1.aboutMe=this.user.aboutMe
      user1.weight=this.user.weight
      user1.height=this.user.height
      user1.image=this.user.image
      user1.address=this.user.address
      user1.email=this.user.email
      user1.workoutroutine=this.user.workoutroutine

      this.service.UpdateProfile(user1).subscribe(res=>{
        this.alert.msg="Profile updated"

      })
    }

    UpdatePassword(){

    this.service.CheckCurrentPasswodr(this.cpass,sessionStorage.getItem("id")).subscribe(r=>{
      if(r){
        this.service.UpdatePassword(this.npass,sessionStorage.getItem("id")).subscribe(result=>{
          if(result){
            this.alert.msg="Password Updated"

          }
        })
      }
      else{
        this.alert.msg="verify your password"
        this.alert.type="danger"

      }
    })


    }

  checkPass(){
    if(this.npass ==this.cnpass){
      this.visiblePass=false
      this.matchPass=false;
      this.isButtonDisabled=false;
    }
    else{
      this.visiblePass=true;
      this.matchPass=true;
      this.isButtonDisabled=true;
    }
  }
  closeAlert(){
    this.good=false
  }

}

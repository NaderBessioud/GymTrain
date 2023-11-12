import {Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../../Services/authentication.service";
import {User} from "../../Models/user";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FloatLabelType} from "@angular/material/form-field";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  form: FormGroup=new FormBuilder().group({});
  formR: FormGroup=new FormBuilder().group({});
  user:User = new  User();
  email:string="";
  password :string = ""
  isButtonDisabled = false
  avatarImage: string = 'assets/images/gymlogin.png'; // Set the initial image
  isLoginTab: boolean = true; // Track which tab is active
  visiblePass:boolean=false;
  matchPass:boolean=false;
  PassMatch:string="2 password does not match";
  pass:string = ""
  good:boolean=false
  dismissible = true;
  alert:any =
    {
      type: 'danger',
      msg: `Invalid email or password.`
    }


  constructor(private authservice:AuthenticationService, private fb:FormBuilder,
              private router:Router,
              public dialogRef: MatDialogRef<AuthenticationComponent>,
              @Inject(MAT_DIALOG_DATA) public cause:string) { }




  switchToLogin() {
    this.avatarImage = this.isLoginTab ?'assets/images/gymregister.png' : 'assets/images/gymlogin.png';
    this.isLoginTab = true;


  }

  switchToRegistration() {

    this.avatarImage = this.isLoginTab ?   'assets/images/gymregister.png' : 'assets/images/gymlogin.png';
    this.isLoginTab = false;

  }


  Login(){
    if(this.cause=="login"){
    this.authservice.Login(this.email, this.password).subscribe(res=>{
      if(res != null){
        sessionStorage.setItem("token",res.token);

        sessionStorage.setItem("email",res.user.email);
        sessionStorage.setItem("id",res.user.idu);
        this.email = ""
        this.password = ""
        this.dialogRef.close()
        this.router.navigate(['ironwave/WelcomePage'])

      }
      else{
        this.email = ""
        this.password = ""
        this.good=true
      }

    },
    (error) => {
        // Handle error response

          this.email = ""
          this.password = ""
          this.good=true
          console.log('Internal Server Error:');
          // Handle other 5xx errors if needed

      }
    )
    }
    else{

      this.authservice.Login(this.email, this.password).subscribe(res=>{
        if(res != null){
          sessionStorage.setItem("token",res.refreshtoken);

          sessionStorage.setItem("email",res.user.email);
          sessionStorage.setItem("id",res.user.idu);
          this.email = ""
          this.password = ""
          this.dialogRef.close()
          this.router.navigate(['ironwave/WelcomePage'])

        }
        else{
          this.email = ""
          this.password = ""
          this.good=true
        }

      })

    }
  }

  Register(){
    this.authservice.Register(this.user).subscribe(u=>{
      sessionStorage.setItem("email",u.email);
    })
  }

  checkemail(){

this.authservice.Checkemail(this.user.email).subscribe(e=>{

  if(e){
    this.alert.msg="Email already exist"
    this.good=true

    this.isButtonDisabled = true
  }
  else{
    this.isButtonDisabled = false
  }
})
  }

  checkPass(){
    if(this.user.password ==this.pass){
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

  ngOnInit(): void {


    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100)]
      ]

    })

    this.formR = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100)]
      ],
      confirmpassword: ['', [Validators.required,

        Validators.minLength(8),
        Validators.maxLength(100)]
    ]
    })

  }

closeAlert(){
    this.good=false
}



}

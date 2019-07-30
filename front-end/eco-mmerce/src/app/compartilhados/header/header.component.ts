import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../auth/token-storage.service';
import {AuthLoginInfo} from '../../auth/login-info';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private loginInfo: AuthLoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }

  goToCadastro() {
    this.router.navigate(['/cadastro']);
  }

  logout() {
    this.tokenStorage.signOut();
  }

  onSubmit() {
    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password
    );

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveTokenType(data.tokenType);
        this.tokenStorage.saveRoleType('user');

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['home']);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }
}

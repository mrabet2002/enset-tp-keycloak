import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'ecom-frontend';

  isLoggedIn = this.keycloakService.isLoggedIn();
  profile: KeycloakProfile | null = null;

  constructor(
    private keycloakService: KeycloakService
  ) {}

  ngOnInit() {
    this.keycloakService.loadUserProfile().then(profile => {
      this.profile = profile;
    });
  }

  handleLogin() {
    this.keycloakService.login();
  }

  handleLogout() {
    this.keycloakService.logout();
  }
}

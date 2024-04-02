import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DemoAngularMaterailModule } from 'src/app/DemoAngularMaterialModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserRoutingModule } from './user-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { DeckListComponent } from './components/deck-list/deck-list.component';
import { CreateDeckComponent } from './components/create-deck/create-deck.component';
import { EditDeckComponent } from './components/edit-deck/edit-deck.component';
import { ViewDeckComponent } from './components/view-deck/view-deck.component';


@NgModule({
  declarations: [
    DashboardComponent,
    ProfileComponent,
    ChangePasswordComponent,
    DeckListComponent,
    CreateDeckComponent,
    EditDeckComponent,
    ViewDeckComponent
    ],
  imports: [
    CommonModule,
    UserRoutingModule,
    DemoAngularMaterailModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ]
})
export class UserModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserGuard } from 'src/app/auth/auth-guards/authUser/user.guard';
import { ProfileComponent } from './components/profile/profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { CreateDeckComponent } from './components/create-deck/create-deck.component';
import { DeckListComponent } from './components/deck-list/deck-list.component';
import { EditDeckComponent } from './components/edit-deck/edit-deck.component';
import { ViewDeckComponent } from './components/view-deck/view-deck.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [UserGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [UserGuard] },
  { path: 'change-password', component: ChangePasswordComponent, canActivate: [UserGuard] },
  { path: 'deck-list', component: DeckListComponent },
  { path: 'create-deck', component: CreateDeckComponent },
  { path: 'view-deck/:deckId', component: ViewDeckComponent },
  { path: 'edit-deck/:id', component: EditDeckComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

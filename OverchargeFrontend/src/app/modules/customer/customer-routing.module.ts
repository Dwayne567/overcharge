import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerGuard } from 'src/app/auth/auth-guards/authCustomer/customer.guard';
import { ProfileComponent } from './components/profile/profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { CreateDeckComponent } from './components/create-deck/create-deck.component';
import { DeckListComponent } from './components/deck-list/deck-list.component';
import { EditDeckComponent } from './components/edit-deck/edit-deck.component';
import { ViewDeckComponent } from './components/view-deck/view-deck.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [CustomerGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [CustomerGuard] },
  { path: 'change_password', component: ChangePasswordComponent, canActivate: [CustomerGuard] },
  { path: 'deck-list', component: DeckListComponent },
  { path: 'create-deck', component: CreateDeckComponent },
  { path: 'view-deck/:deckId', component: ViewDeckComponent },
  { path: 'edit-deck/:id', component: EditDeckComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

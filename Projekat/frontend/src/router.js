import Vue from 'vue'
import Router from 'vue-router'

import editProfile from '@/components/account/editProfile'
import homeContainer from '@/components/home/home-container'
import mealsContainer from '@/components/meals/meals-container'
import recepiesContainer from '@/components/recepies/recepies-container'
import recepieDetails from '@/components/recepies/recepie-details'
import addGrocerie from '@/components/grocerie/add-grocerie'
import addMeal from '@/components/meals/add-meal'
import editMeal from '@/components/meals/edit-meal'

import findMeal from '@/components/meals/find-meal'
import login from '@/components/account/login'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/edit-profile',
            component: editProfile
        },
        {
            path: '/',
            component: homeContainer
        },
        {
            path: '/meals',
            component: mealsContainer
        },
        {
            path: '/recepies',
            component: recepiesContainer
        },
        {
            path: '/details/:id',
            component: recepieDetails
        },
        {
            path: '/meals-table',
            component: addGrocerie
        },
        {
            path: '/add-meal',
            component: addMeal
        },
        {
            path: '/edit-meal/:id',
            component: editMeal
        },
        {
            path: '/find-meal',
            component: findMeal
        },
        {
            path: '/login',
            component: login
        }

    ]
})

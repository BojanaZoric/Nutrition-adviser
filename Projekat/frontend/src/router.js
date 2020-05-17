import Vue from 'vue'
import Router from 'vue-router'

import editProfile from '@/components/account/editProfile'
import homeContainer from '@/components/home/home-container'
import mealsContainer from '@/components/meals/meals-container'
import recepiesContainer from '@/components/recepies/recepies-container'
import recepieDetails from '@/components/recepies/recepie-details'


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
            path: '/details',
            component: recepieDetails
        }

    ]
})

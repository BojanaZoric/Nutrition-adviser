import Vue from 'vue'
import Router from 'vue-router'

import editProfile from '@/components/account/editProfile'
import homeContainer from '@/components/home/home-container'
import mealsContainer from '@/components/meals/meals-container'


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
        }

    ]
})

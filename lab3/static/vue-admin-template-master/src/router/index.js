import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/student/allStudents',
    name: '学生管理',
    meta: { title: '学生管理', icon: 'example' },
    children: [
      {
        path: 'allStudents',
        name: '学生列表',
        component: () => import('@/views/db/student/list'),
        meta: { title: '学生列表', icon: 'tree' }
      },
      {
        path: 'addStudent',
        name: '添加学生',
        component: () => import('@/views/db/student/save'),
        meta: { title: '添加学生', icon: 'table' }
      },
      {
        path: 'update/:id',
        name: '修改学生信息',
        component: () => import('@/views/db/student/save'),
        meta: { title: '修改学生信息', icon: 'table' },
        hidden: true
      }
    ]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/getAllTeachers',
    name: '教师管理',
    meta: { title: '教师管理', icon: 'example' },
    children: [
      {
        path: 'getAllTeachers',
        name: '教师列表',
        component: () => import('@/views/db/teacher/list'),
        meta: { title: '教师列表', icon: 'tree' }
      },
      {
        path: 'insertTeacher',
        name: '添加老师',
        component: () => import('@/views/db/teacher/save'),
        meta: { title: '添加老师', icon: 'table' }
      }
    ]
  },

  {
    path: '/department',
    component: Layout,
    redirect: '/department/getAllDepartments',
    name: '院系管理',
    meta: { title: '院系管理', icon: 'example' },
    children: [
      {
        path: 'getAllDepartments',
        name: '院系列表',
        component: () => import('@/views/db/department/list'),
        meta: { title: '院系列表', icon: 'tree' }
      }
    ]
  },

  {
    path: '/class',
    component: Layout,
    redirect: '/class/getAllClasses',
    name: '班级管理',
    meta: { title: '班级管理', icon: 'example' },
    children: [
      {
        path: 'getAllClasses',
        name: '班级列表',
        component: () => import('@/views/db/class/list'),
        meta: { title: '班级列表', icon: 'tree' }
      },
      {
        path: 'insertClass',
        name: '添加班级',
        component: () => import('@/views/db/class/save'),
        meta: { title: '添加班级', icon: 'table' }
      }
    ]
  },

  {
    path: '/course',
    component: Layout,
    redirect: '/course/getAllCourses',
    name: '课程管理',
    meta: { title: '课程管理', icon: 'example' },
    children: [
      {
        path: 'getAllCourses',
        name: '课程列表',
        component: () => import('@/views/db/course/list'),
        meta: { title: '课程列表', icon: 'tree' }
      },
      {
        path: 'insertCourse',
        name: '添加课程',
        component: () => import('@/views/db/course/save'),
        meta: { title: '添加课程', icon: 'table' }
      }
    ]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

<template>
  <el-header class="bg-white shadow-sm border-b border-gray-200 dark:bg-gray-800 dark:border-gray-700">
    <div class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between items-center h-16">
        <!-- Logo -->
        <router-link to="/" class="flex items-center space-x-2">
          <div class="w-8 h-8 bg-primary-600 rounded-lg flex items-center justify-center">
            <span class="text-white font-bold text-lg">B</span>
          </div>
          <span class="text-xl font-semibold text-gray-900 dark:text-white">技术博客</span>
        </router-link>

        <!-- Navigation -->
        <nav class="hidden md:flex space-x-8">
          <router-link 
            to="/" 
            class="text-gray-600 hover:text-primary-600 dark:text-gray-300 dark:hover:text-primary-400 px-3 py-2 rounded-md text-sm font-medium transition-colors"
            active-class="text-primary-600 dark:text-primary-400"
          >
            首页
          </router-link>
          <router-link 
            to="/blog" 
            class="text-gray-600 hover:text-primary-600 dark:text-gray-300 dark:hover:text-primary-400 px-3 py-2 rounded-md text-sm font-medium transition-colors"
            active-class="text-primary-600 dark:text-primary-400"
          >
            博客
          </router-link>
          <router-link 
            to="/about" 
            class="text-gray-600 hover:text-primary-600 dark:text-gray-300 dark:hover:text-primary-400 px-3 py-2 rounded-md text-sm font-medium transition-colors"
            active-class="text-primary-600 dark:text-primary-400"
          >
            关于
          </router-link>
        </nav>

        <!-- Right side actions -->
        <div class="flex items-center space-x-4">
          <!-- Search button -->
          <el-button 
            type="primary" 
            text 
            @click="$emit('toggleSearch')"
            class="!p-2"
          >
            <el-icon size="20"><Search /></el-icon>
          </el-button>

          <!-- Dark mode toggle -->
          <el-button 
            type="primary" 
            text 
            @click="toggleDarkMode"
            class="!p-2"
          >
            <el-icon v-if="!isDarkMode" size="20"><Moon /></el-icon>
            <el-icon v-else size="20"><Sunny /></el-icon>
          </el-button>

          <!-- Mobile menu button -->
          <el-button 
            type="primary" 
            text 
            class="md:!hidden !p-2"
            @click="$emit('toggleMobileMenu')"
          >
            <el-icon size="20"><Menu /></el-icon>
          </el-button>
        </div>
      </div>
    </div>
  </el-header>
</template>

<script setup>
import { useUIStore } from '@/stores/useUIStore'
import { storeToRefs } from 'pinia'

const uiStore = useUIStore()
const { isDarkMode } = storeToRefs(uiStore)

const toggleDarkMode = () => {
  uiStore.toggleDarkMode()
}

defineEmits(['toggleSearch', 'toggleMobileMenu'])
</script>

<style scoped>
.router-link-active {
  @apply text-primary-600 dark:text-primary-400;
}
</style>
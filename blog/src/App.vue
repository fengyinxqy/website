<script setup>
import AppHeader from '@/components/layout/AppHeader.vue';
import MobileMenu from '@/components/layout/MobileMenu.vue';
import { useUIStore } from '@/stores/useUIStore';
import { ref, onMounted } from 'vue';

const uiStore = useUIStore();
const isMobileMenuOpen = ref(false);

// 初始化暗黑模式
onMounted(() => {
  const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
  if (prefersDark) {
    uiStore.toggleDarkMode();
  }
});

const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value;
};

const closeMobileMenu = () => {
  isMobileMenuOpen.value = false;
};
</script>

<template>
  <div :class="{ dark: uiStore.isDarkMode }">
    <el-config-provider>
      <div class="min-h-screen bg-gray-50 dark:bg-gray-900 transition-colors duration-300">
        <AppHeader @toggle-mobile-menu="toggleMobileMenu" />
        <main>
          <router-view />
        </main>
        <MobileMenu
          :is-open="isMobileMenuOpen"
          @close="closeMobileMenu"
        />
      </div>
    </el-config-provider>
  </div>
</template>

<style>
/* 全局样式 */
#app {
  min-height: 100vh;
}

/* 暗黑模式过渡动画 */
* {
  transition:
    background-color 0.3s ease,
    color 0.3s ease,
    border-color 0.3s ease;
}

/* 滚动条样式 */
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: #f1f5f9;
}

.dark ::-webkit-scrollbar-track {
  background: #1f2937;
}

::-webkit-scrollbar-thumb {
  background: #cbd5e1;
  border-radius: 4px;
}

.dark ::-webkit-scrollbar-thumb {
  background: #4b5563;
}

::-webkit-scrollbar-thumb:hover {
  background: #94a3b8;
}

.dark ::-webkit-scrollbar-thumb:hover {
  background: #6b7280;
}
</style>

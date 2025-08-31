<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 py-8">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Page Header -->
      <div class="text-center mb-12">
        <h1 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-4">
          关于我
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300">
          一名热爱技术的前端开发者，喜欢分享和学习
        </p>
      </div>

      <!-- Main Content -->
      <div
        class="bg-white dark:bg-gray-800 rounded-lg p-8 shadow-sm border border-gray-200 dark:border-gray-700"
      >
        <!-- Profile Section -->
        <div class="flex flex-col md:flex-row items-center gap-8 mb-12">
          <div
            class="w-32 h-32 bg-primary-100 dark:bg-primary-900 rounded-full flex items-center justify-center"
          >
            <span class="text-4xl font-bold text-primary-600 dark:text-primary-400">Dev</span>
          </div>
          <div class="flex-1">
            <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-4">
              前端开发者
            </h2>
            <p class="text-gray-600 dark:text-gray-300 mb-4">
              专注于现代Web开发技术，热爱Vue.js、React、TypeScript等前端技术栈。
              喜欢通过博客记录学习过程和技术实践，与大家分享开发经验和解决方案。
            </p>
            <div class="flex flex-wrap gap-3">
              <span
                class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm rounded-full"
              >
                Vue.js
              </span>
              <span
                class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm rounded-full"
              >
                React
              </span>
              <span
                class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm rounded-full"
              >
                TypeScript
              </span>
              <span
                class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm rounded-full"
              >
                Node.js
              </span>
            </div>
          </div>
        </div>

        <!-- Skills Section -->
        <div class="mb-12">
          <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-6">
            技术栈
          </h2>
          <div class="grid md:grid-cols-2 gap-6">
            <div>
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-4">
                前端技术
              </h3>
              <div class="space-y-3">
                <div
                  v-for="skill in frontendSkills"
                  :key="skill.name"
                  class="flex items-center justify-between"
                >
                  <span class="text-gray-600 dark:text-gray-300">{{ skill.name }}</span>
                  <el-progress
                    :percentage="skill.percentage"
                    :stroke-width="8"
                    :color="skill.color"
                    class="w-24"
                  />
                </div>
              </div>
            </div>
            <div>
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-4">
                其他技术
              </h3>
              <div class="space-y-3">
                <div
                  v-for="skill in otherSkills"
                  :key="skill.name"
                  class="flex items-center justify-between"
                >
                  <span class="text-gray-600 dark:text-gray-300">{{ skill.name }}</span>
                  <el-progress
                    :percentage="skill.percentage"
                    :stroke-width="8"
                    :color="skill.color"
                    class="w-24"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Blog Stats -->
        <div class="mb-12">
          <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-6">
            博客统计
          </h2>
          <div class="grid grid-cols-2 md:grid-cols-4 gap-6">
            <div class="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
              <div class="text-3xl font-bold text-primary-600 dark:text-primary-400 mb-2">
                12
              </div>
              <div class="text-sm text-gray-600 dark:text-gray-300">
                文章总数
              </div>
            </div>
            <div class="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
              <div class="text-3xl font-bold text-green-600 dark:text-green-400 mb-2">
                1.2K
              </div>
              <div class="text-sm text-gray-600 dark:text-gray-300">
                总阅读量
              </div>
            </div>
            <div class="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
              <div class="text-3xl font-bold text-blue-600 dark:text-blue-400 mb-2">
                6
              </div>
              <div class="text-sm text-gray-600 dark:text-gray-300">
                分类数量
              </div>
            </div>
            <div class="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
              <div class="text-3xl font-bold text-purple-600 dark:text-purple-400 mb-2">
                24
              </div>
              <div class="text-sm text-gray-600 dark:text-gray-300">
                标签数量
              </div>
            </div>
          </div>
        </div>

        <!-- Contact Section -->
        <div>
          <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-6">
            联系我
          </h2>
          <div class="grid md:grid-cols-2 gap-6">
            <div>
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-4">
                社交媒体
              </h3>
              <div class="space-y-3">
                <a
                  href="#"
                  class="flex items-center space-x-3 text-gray-600 dark:text-gray-300 hover:text-primary-600 dark:hover:text-primary-400 transition-colors"
                >
                  <el-icon size="20"><Link /></el-icon>
                  <span>GitHub</span>
                </a>
                <a
                  href="#"
                  class="flex items-center space-x-3 text-gray-600 dark:text-gray-300 hover:text-primary-600 dark:hover:text-primary-400 transition-colors"
                >
                  <el-icon size="20"><Link /></el-icon>
                  <span>Twitter</span>
                </a>
                <a
                  href="#"
                  class="flex items-center space-x-3 text-gray-600 dark:text-gray-300 hover:text-primary-600 dark:hover:text-primary-400 transition-colors"
                >
                  <el-icon size="20"><Link /></el-icon>
                  <span>LinkedIn</span>
                </a>
              </div>
            </div>
            <div>
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-4">
                其他方式
              </h3>
              <div class="space-y-3">
                <div class="flex items-center space-x-3 text-gray-600 dark:text-gray-300">
                  <el-icon size="20">
                    <Message />
                  </el-icon>
                  <span>email@example.com</span>
                </div>
                <div class="flex items-center space-x-3 text-gray-600 dark:text-gray-300">
                  <el-icon size="20">
                    <ChatDotRound />
                  </el-icon>
                  <span>Discord: username</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Call to Action -->
      <div class="text-center mt-12">
        <p class="text-gray-600 dark:text-gray-300 mb-6">
          对某个技术话题感兴趣？或者想要讨论合作机会？
        </p>
        <el-button
          type="primary"
          size="large"
          class="!px-8"
        >
          发送消息
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
const frontendSkills = [
  { name: 'Vue.js', percentage: 90, color: '#42b883' },
  { name: 'React', percentage: 85, color: '#61dafb' },
  { name: 'TypeScript', percentage: 88, color: '#3178c6' },
  { name: 'JavaScript', percentage: 92, color: '#f7df1e' },
];

const otherSkills = [
  { name: 'Node.js', percentage: 80, color: '#339933' },
  { name: 'CSS/SCSS', percentage: 85, color: '#cc6699' },
  { name: 'Git', percentage: 88, color: '#f05032' },
  { name: 'Docker', percentage: 70, color: '#2496ed' },
];
</script>

<style scoped>
.el-progress :deep(.el-progress-bar) {
  padding-right: 0;
}

.el-progress :deep(.el-progress-bar__inner) {
  border-radius: 4px;
}
</style>

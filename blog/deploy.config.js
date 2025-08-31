// CloudStudio 部署配置
export default {
  // 构建输出目录
  outputDir: 'dist',

  // 静态资源路径
  publicPath: process.env.NODE_ENV === 'production' ? './' : '/',

  // 构建配置
  build: {
    //  chunkSizeWarningLimit: 1000,
    rollupOptions: {
      output: {
        manualChunks: {
          'element-plus': ['element-plus'],
          'vue-vendor': ['vue', 'vue-router', 'pinia'],
        },
      },
    },
  },

  // 开发服务器配置
  devServer: {
    port: 5173,
    host: '0.0.0.0',
    open: true,
  },
};

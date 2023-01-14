package ru.spliterash.itemsAdderHelper.command.configuration

import co.aikar.commands.PaperCommandManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ACFConfiguration {
    @Bean
    open fun paperCommandManager(holder: ACFCommandManagerHolder): PaperCommandManager {
        return holder.instance
    }
}
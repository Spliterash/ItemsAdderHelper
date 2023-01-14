package ru.spliterash.itemsAdderHelper.command.configuration

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
open class CommandBeanPostProcessor : DestructionAwareBeanPostProcessor {
    @Autowired
    private lateinit var commandManager: PaperCommandManager

    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is BaseCommand) commandManager.registerCommand(bean)
        return bean
    }

    override fun postProcessBeforeDestruction(bean: Any, beanName: String) {
        if (bean is BaseCommand) commandManager.unregisterCommand(bean)
    }
}
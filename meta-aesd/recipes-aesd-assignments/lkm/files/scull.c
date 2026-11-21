#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("diferrans1");
MODULE_DESCRIPTION("Scull module stub");

static int __init scull_init(void)
{
    printk(KERN_INFO "scull: loaded\n");
    return 0;
}

static void __exit scull_exit(void)
{
    printk(KERN_INFO "scull: unloaded\n");
}

module_init(scull_init);
module_exit(scull_exit);

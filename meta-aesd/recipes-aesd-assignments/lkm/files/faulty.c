#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("diferrans1");
MODULE_DESCRIPTION("Faulty module stub");

static int __init faulty_init(void)
{
    printk(KERN_INFO "faulty: loaded\n");
    return 0;
}

static void __exit faulty_exit(void)
{
    printk(KERN_INFO "faulty: unloaded\n");
}

module_init(faulty_init);
module_exit(faulty_exit);

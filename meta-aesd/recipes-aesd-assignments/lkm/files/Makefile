ifneq ($(KERNELRELEASE),)
obj-m := hello.o faulty.o scull.o
else
KERNELDIR ?= $(KERNEL_SRC)
ifeq ($(KERNELDIR),)
KERNELDIR := /lib/modules/$(shell uname -r)/build
endif
PWD := $(shell pwd)

modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules

modules_install:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules_install

endif

clean:
	rm -rf *.o *~ core .depend .*.cmd *.ko *.mod.c .tmp_versions modules.order Module.symvers

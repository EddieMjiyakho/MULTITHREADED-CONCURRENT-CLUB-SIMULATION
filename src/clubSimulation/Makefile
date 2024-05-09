#Sakhile Mjiyakho
#Makefile

.SUFFIXES: .java .class
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
SRCDIR=src/clubSimulation
BINDIR=bin

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath src $<
	
CLASSES2=Clubgoer.class\
	ClubGrid.class\
	ClubView.class\
	GridBlock.class\
	CounterDisplay.class\
	PeopleCounter.class\
	PeopleLocation.class\
	ClubSimulation.class
	
CLASSES=$(CLASSES2:%.class=$(BINDIR)/%.class)

default: $(CLASSES)

run: $(CLASSES)
	$(JAVA) -cp $(BINDIR) clubSimulation.ClubSimulation $(ARGS)
	
clean:
	rm $(BINDIR)/clubSimulation/*.class